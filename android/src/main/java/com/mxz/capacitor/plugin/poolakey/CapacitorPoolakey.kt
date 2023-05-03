package com.mxz.capacitor.plugin.poolakey

import android.app.Activity
import android.content.Context
import android.util.Log

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
}