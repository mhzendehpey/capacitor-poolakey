package com.mxz.capacitor.plugin.poolakey

import android.content.Context
import android.util.Log

import ir.cafebazaar.poolakey.Connection
import ir.cafebazaar.poolakey.ConnectionState
import ir.cafebazaar.poolakey.Payment
import ir.cafebazaar.poolakey.config.PaymentConfiguration
import ir.cafebazaar.poolakey.config.SecurityCheck
import ir.cafebazaar.poolakey.exception.DisconnectException
import ir.cafebazaar.poolakey.request.PurchaseRequest

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
        throwable.message?.let { Log.d(LOG_TAG, "Connection Failed $it") }
      }
      disconnected {
        Log.d(LOG_TAG, "Connection Disconnected")
      }
    }
  }

  fun disconnectPayment() {
    paymentConnection?.disconnect()
  }
}