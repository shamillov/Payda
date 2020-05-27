package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName

data class DonationActiveData(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val donationTitle: String,
    @SerializedName("description")
    val donationDescription: String,
    @SerializedName("amount")
    val donationAmount: Int,
    @SerializedName("region")
    val fundLocation: String,
    @SerializedName("fundId")
    val fundId: Int,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("donations")
    val donationProgress: Int
)