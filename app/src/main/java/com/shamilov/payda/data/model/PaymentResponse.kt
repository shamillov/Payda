package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName

data class PaymentResponse (
    @SerializedName("body")
    val body: String,
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("statusCodeValue")
    val statusCodeValue: Int
)