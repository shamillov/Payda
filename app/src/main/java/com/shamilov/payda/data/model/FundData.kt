package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Shamilov on 22.06.2020
 */

data class FundData (
    @SerializedName("bg")
    var bg: String,

    @SerializedName("bgContentType")
    var bgContentType: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("id")
    var id: Int,

    @SerializedName("logo")
    var logo: String,

    @SerializedName("logoContentType")
    var logoContentType: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("paymentSystemId")
    var paymentSystemId: Int,

    @SerializedName("region")
    var region: String,

    @SerializedName("users")
    var users: List<UserData>
)