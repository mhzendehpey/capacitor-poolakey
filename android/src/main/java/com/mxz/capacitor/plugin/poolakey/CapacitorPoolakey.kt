package com.mxz.capacitor.plugin.poolakey

import android.app.Activity
import android.content.Context
import android.util.Log
import com.getcapacitor.JSObject
import com.getcapacitor.PluginCall
import ir.cafebazaar.poolakey.Connection
import ir.cafebazaar.poolakey.ConnectionState
import ir.cafebazaar.poolakey.Payment
import ir.cafebazaar.poolakey.config.PaymentConfiguration
import ir.cafebazaar.poolakey.config.SecurityCheck
import org.json.JSONArray

const val LOG_TAG = "CAP_POOLAKEY"

class CapacitorPoolakey {
    private var paymentConnection: Connection? = null
    private lateinit var payment: Payment

    fun connectPayment(call: PluginCall, context: Context) {
        val rsaPublicKey = call.getString("rsaPublicKey")
        val securityCheck = if (rsaPublicKey == null) {
            SecurityCheck.Disable
        } else {
            SecurityCheck.Enable(rsaPublicKey = rsaPublicKey)
        }

        val paymentConfig = PaymentConfiguration(localSecurityCheck = securityCheck)
        payment = Payment(context = context, config = paymentConfig)

        runIfPaymentInitialized(call) {
            paymentConnection = payment.connect {
                connectionSucceed {
                    Log.d(LOG_TAG, "Connection Succeed")
                    call.resolve()
                }
                connectionFailed { throwable ->
                    Log.d(LOG_TAG, "Connection Failed: ${throwable.message}")
                    call.reject("Connection Failed", Exception(throwable))
                }
                disconnected {
                    Log.d(LOG_TAG, "Connection Disconnected")
                    call.reject("Connection Disconnected", Exception("Connection Disconnected"))
                }
            }
        }
    }

    fun disconnectPayment(call: PluginCall) {
        paymentConnection?.disconnect()
        call.resolve()
    }

    private fun startActivity(
        call: PluginCall,
        activity: Activity?,
        command: PaymentActivity.Command,
        productId: String,
        payload: String?,
        dynamicPriceToken: String?
    ) {
        if (paymentConnection?.getState() != ConnectionState.Connected) {
            Log.d(LOG_TAG, "Payment is not connected")
            call.reject(
                "Payment is not connected",
                IllegalStateException("Payment is not connected")
            )
            return
        }
        if (activity == null) {
            Log.d(LOG_TAG, "Activity not found")
            call.reject("Activity not found", IllegalStateException("Activity not found"))
            return
        }

        PaymentActivity.start(
            call,
            activity,
            command,
            productId,
            payment,
            payload,
            dynamicPriceToken
        )
    }

    fun purchaseProduct(
        call: PluginCall,
        activity: Activity?,
    ) {
        val productId = call.getString("productId")!!
        val payload = call.getString("payload")
        val dynamicPriceToken = call.getString("dynamicPriceToken")

        runIfPaymentInitialized(call) {
            startActivity(
                call,
                activity,
                PaymentActivity.Command.Purchase,
                productId,
                payload,
                dynamicPriceToken
            )
        }
    }

    fun subscribeProduct(
        call: PluginCall,
        activity: Activity?,
    ) {
        val productId = call.getString("productId")!!
        val payload = call.getString("payload")
        val dynamicPriceToken = call.getString("dynamicPriceToken")

        runIfPaymentInitialized(call) {
            startActivity(
                call,
                activity,
                PaymentActivity.Command.Subscribe,
                productId,
                payload,
                dynamicPriceToken
            )
        }
    }

    fun consumePurchase(call: PluginCall) {
        val purchaseToken = call.getString("purchaseToken")!!

        runIfPaymentInitialized(call) {
            payment.consumeProduct(purchaseToken) {
                consumeSucceed {
                    Log.d(LOG_TAG, "consume succeed")
                    call.resolve()
                }
                consumeFailed {
                    Log.d(LOG_TAG, "consume failed: ${it.message}")
                    call.reject("consume failed", Exception(it))
                }
            }
        }
    }

    fun getPurchasedProducts(call: PluginCall) {
        runIfPaymentInitialized(call) {
            payment.getPurchasedProducts {
                queryFailed { call.reject("query failed: ${it.message}", Exception(it)) }
                querySucceed {
                    val jsObject = JSObject()
                    jsObject.put("list", JSONArray(it.toJsonString()))
                    call.resolve(jsObject)
                }
            }
        }
    }

    fun getSubscribedProducts(call: PluginCall) {
        runIfPaymentInitialized(call) {
            payment.getSubscribedProducts {
                queryFailed { call.reject("query failed: ${it.message}", Exception(it)) }
                querySucceed {
                    val jsObject = JSObject()
                    jsObject.put("list", JSONArray(it.toJsonString()))
                    call.resolve(jsObject)
                }
            }
        }
    }

    fun queryPurchaseProduct(call: PluginCall) {
        val productId = call.getString("productId")
        runIfPaymentInitialized(call) {
            payment.getPurchasedProducts {
                queryFailed { call.reject("query failed: ${it.message}", Exception(it)) }
                querySucceed { purchaseList ->
                    val product = purchaseList.firstOrNull {
                        it.productId == productId
                    }

                    if (product == null) {
                        call.reject("item not found", Exception("NotFoundException"))
                    } else {
                        val jsObject = JSObject(product.originalJson)
                        call.resolve(jsObject)
                    }
                }
            }
        }
    }

    fun querySubscribeProduct(call: PluginCall) {
        val productId = call.getString("productId")
        runIfPaymentInitialized(call) {
            payment.getSubscribedProducts {
                queryFailed { call.reject("query failed: ${it.message}", Exception(it)) }
                querySucceed { purchaseList ->
                    val product = purchaseList.firstOrNull {
                        it.productId == productId
                    }

                    if (product == null) {
                        call.reject("item not found", Exception("NotFoundException"))
                    } else {
                        val jsObject = JSObject(product.originalJson)
                        call.resolve(jsObject)
                    }
                }
            }
        }
    }

    fun getInAppSkuDetails(call: PluginCall) {
        val productIdsJson = call.getString("productIdsJson")!!
        val productIds = parseProductIds(productIdsJson)

        runIfPaymentInitialized(call) {
            payment.getInAppSkuDetails(productIds) {
                getSkuDetailsFailed {
                    call.reject(
                        "get Sku details failed: ${it.message}",
                        Exception(it)
                    )
                }
                getSkuDetailsSucceed {
                    val jsObject = JSObject(it.toJsonString())
                    call.resolve(jsObject)
                }
            }
        }
    }

    fun getSubscriptionSkuDetails(call: PluginCall) {
        val productIdsJson = call.getString("productIdsJson")!!
        val productIds = parseProductIds(productIdsJson)

        runIfPaymentInitialized(call) {
            payment.getSubscriptionSkuDetails(productIds) {
                getSkuDetailsFailed {
                    call.reject(
                        "get Sku details failed: ${it.message}",
                        Exception(it)
                    )
                }
                getSkuDetailsSucceed {
                    val jsObject = JSObject(it.toJsonString())
                    call.resolve(jsObject)
                }
            }
        }
    }

    private fun runIfPaymentInitialized(call: PluginCall, runner: () -> Unit) {
        if (::payment.isInitialized.not()) {
            call.reject(
                "Payment is not initialized",
                IllegalStateException("Payment is not initialized")
            )
            return
        }

        runner.invoke()
    }

}