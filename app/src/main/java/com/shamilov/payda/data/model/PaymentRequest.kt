package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName

data class PaymentRequest (
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("paymentToken")
    val paymentToken: String
)