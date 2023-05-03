package com.mxz.capacitor.plugin.poolakey

import ir.cafebazaar.poolakey.entity.PurchaseInfo

fun List<PurchaseInfo>.toJsonString(): String {
    val originalsJson = map { it.originalJson }
    return "[" + originalsJson.joinToString() + "]"
}