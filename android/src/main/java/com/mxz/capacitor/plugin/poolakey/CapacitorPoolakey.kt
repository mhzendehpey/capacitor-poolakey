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

const val LOG_TAG = "CAP_POOLAKEY"

class CapacitorPoolakey {
  private var paymentConnection: Connection? = null
  private lateinit var payment: Payment

  fun echo(value: String?): String? {
    Log.i("Echo", value!!)
    return value
  }

  fun connectPayment(context: Context, rsaPublicKey: String? = null) {
    val securityCheck = if (rsaPublicKey == null) {
      SecurityCheck.Disable
    } else {
      SecurityCheck.Enable(rsaPublicKey = rsaPublicKey)
    }

    val paymentConfig = PaymentConfiguration(localSecurityCheck = securityCheck)
    val payment = Payment(context = context, config = paymentConfig)

    paymentConnection = payment.connect {
      connectionSucceed {
        Log.d(LOG_TAG, "Connection Succeed")
      }
      connectionFailed { throwable ->
        throwable.message?.let { Log.d(LOG_TAG, "Connection Failed: $it") }
      }
      disconnected {
        Log.d(LOG_TAG, "Connection Disconnected")
      }
    }
  }

  fun disconnectPayment() {
    paymentConnection?.disconnect()
  }

  private fun startActivity(
    activity: Activity?,
    command: PaymentActivity.Command,
    productId: String,
    payload: String?,
    dynamicPriceToken: String?
  ) {
    if (paymentConnection?.getState() != ConnectionState.Connected) {
      Log.d(LOG_TAG, "payment not connected")
      return
    }
    if (activity == null) {
      Log.d(LOG_TAG, "activity not found")
      return
    }

    PaymentActivity.start(
      activity,
      command,
      productId,
      payment,
      payload,
      dynamicPriceToken
    )
  }

  fun purchaseProduct(
    activity: Activity?,
    productId: String,
    payload: String?,
    dynamicPriceToken: String?
  ) {
    startActivity(
      activity,
      PaymentActivity.Command.Purchase,
      productId,
      payload,
      dynamicPriceToken
    )
  }

  fun subscribeProduct(
    activity: Activity?,
    productId: String,
    payload: String?,
    dynamicPriceToken: String?
  ) {
    startActivity(
      activity,
      PaymentActivity.Command.Subscribe,
      productId,
      payload,
      dynamicPriceToken
    )
  }

  fun consumePurchase(purchaseToken: String) {
    payment.consumeProduct(purchaseToken) {
      consumeSucceed {
        Log.d(LOG_TAG, "consume succeed")
      }
      consumeFailed {
        Log.d(LOG_TAG, "consume failed: ${it.message}")
      }
    }
  }

  fun getPurchasedProducts(call: PluginCall) {
    payment.getPurchasedProducts {
      queryFailed { call.reject("query failed: ${it.message}", Exception(it)) }
      querySucceed {
        val jsObject = JSObject(it.toJsonString())
        call.resolve(jsObject)
      }
    }
  }

  fun getSubscribedProducts(call: PluginCall) {
    payment.getSubscribedProducts {
      queryFailed { call.reject("query failed: ${it.message}", Exception(it)) }
      querySucceed {
        val jsObject = JSObject(it.toJsonString())
        call.resolve(jsObject)
      }
    }
  }

  fun queryPurchaseProduct(call: PluginCall) {
    val productId = call.getString("productId")
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

  fun querySubscribeProduct(call: PluginCall) {
    val productId = call.getString("productId")
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

  fun getInAppSkuDetails(call: PluginCall) {
    val productIdsJson = call.getString("productIdsJson")!!
    val productIds = parseProductIds(productIdsJson)

    payment.getInAppSkuDetails(productIds) {
      getSkuDetailsFailed { call.reject("get Sku details failed: ${it.message}", Exception(it)) }
      getSkuDetailsSucceed {
        val jsObject = JSObject(it.toJsonString())
        call.resolve(jsObject)
      }
    }
  }

  fun getSubscriptionSkuDetails(call: PluginCall) {
    val productIdsJson = call.getString("productIdsJson")!!
    val productIds = parseProductIds(productIdsJson)

    payment.getSubscriptionSkuDetails(productIds) {
      getSkuDetailsFailed { call.reject("get Sku details failed: ${it.message}", Exception(it)) }
      getSkuDetailsSucceed {
        val jsObject = JSObject(it.toJsonString())
        call.resolve(jsObject)
      }
    }
  }

}