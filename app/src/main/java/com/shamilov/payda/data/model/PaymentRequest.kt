package com.shamilov.payda.data.model

data class PaymentRequest (
    val amount: String,
    val currency: String,
    val paymentToken: String
)