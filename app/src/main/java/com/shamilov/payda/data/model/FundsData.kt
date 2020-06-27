package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Shamilov on 22.06.2020
 */
data class FundsData (
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("region")
    var region: String,
    @SerializedName("logo")
    var logo: String,
    @SerializedName("logoContentType")
    var logoContentType: String,
    @SerializedName("bg")
    var bg: String,
    @SerializedName("bgContentType")
    var bgContentType: String,
    @SerializedName("paymentSystemId")
    var paymentSystemId: Int,
    @SerializedName("users")
    var users: List<Any>
)