package com.mxz.capacitor.plugin.poolakey

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import com.getcapacitor.JSObject
import com.getcapacitor.PluginCall
import ir.cafebazaar.poolakey.Payment
import ir.cafebazaar.poolakey.callback.PurchaseCallback
import ir.cafebazaar.poolakey.request.PurchaseRequest

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val callback: PurchaseCallback.() -> Unit = {
            purchaseSucceed { purchaseInfo ->
                Log.d(LOG_TAG, purchaseInfo.originalJson)
                val jsObject = JSObject(purchaseInfo.originalJson)
                call.resolve(jsObject)
                finish()
            }
            purchaseCanceled {
                Log.d(LOG_TAG, "Purchase canceled")
                call.reject("Purchase canceled", IllegalStateException("Purchase canceled"))
                finish()
            }
            purchaseFailed { throwable ->
                Log.d(LOG_TAG, "Purchase Failed: ${throwable.message}")
                call.reject("Purchase Failed: ${throwable.message}", Exception(throwable))
                finish()
            }
            purchaseFlowBegan {
                Log.d(LOG_TAG, "Purchase flow began")
            }
            failedToBeginFlow { throwable ->
                Log.d(LOG_TAG, "Purchase flow failed to begin: ${throwable.message}")
                call.reject(
                    "Purchase flow failed to begin: ${throwable.message}",
                    Exception(throwable)
                )
                finish()
            }
        }

        when (command) {
            Command.Purchase -> purchaseProduct(callback)
            Command.Subscribe -> subscribeProduct(callback)
        }
    }

    private fun purchaseProduct(callback: PurchaseCallback.() -> Unit) {
        payment.purchaseProduct(
            activityResultRegistry,
            PurchaseRequest(productId, payload, dynamicPriceToken),
            callback,
        )
    }

    private fun subscribeProduct(callback: PurchaseCallback.() -> Unit) {
        payment.subscribeProduct(
            activityResultRegistry,
            PurchaseRequest(productId, payload, dynamicPriceToken),
            callback,
        )
    }

    companion object {
        private lateinit var call: PluginCall
        private lateinit var command: Command
        private lateinit var productId: String
        private lateinit var payment: Payment
        private var payload: String? = null
        private var dynamicPriceToken: String? = null

        @JvmStatic
        fun start(
            call: PluginCall,
            activity: Activity,
            command: Command,
            productId: String,
            payment: Payment,
            payload: String?,
            dynamicPriceToken: String?,
        ) {
            PaymentActivity.call = call
            PaymentActivity.command = command
            PaymentActivity.productId = productId
            PaymentActivity.payment = payment
            PaymentActivity.payload = payload
            PaymentActivity.dynamicPriceToken = dynamicPriceToken
            val intent = Intent(activity, PaymentActivity::class.java)
            activity.startActivity(intent)
        }
    }

    enum class Command {
        Purchase,
        Subscribe
    }
}