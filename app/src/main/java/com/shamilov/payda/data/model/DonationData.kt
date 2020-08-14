package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName


data class DonationData (
    @SerializedName("amount")
    val amount: Int,

    @SerializedName("description")
    val description: String,

    @SerializedName("donations")
    val donations: Int,

    @SerializedName("fund")
    val fund: FundData,

    @SerializedName("id")
    val id: Int,

    @SerializedName("images")
    val images: List<ImageData>,

    @SerializedName("name")
    val title: String,

    @SerializedName("region")
    val region: String
)