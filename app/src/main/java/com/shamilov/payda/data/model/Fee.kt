package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Shamilov on 22.06.2020
 */
data class Fee(
    @SerializedName("logo")
    val fundLogo: String,
    @SerializedName("name")
    val fundName: String,
    @SerializedName("region")
    val location: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("donations")
    val donationProgress: Int,
    @SerializedName("amount")
    val donationAmount: Int,
    @SerializedName("name")
    val donationTitle: String,
    @SerializedName("description")
    val donationDescription: String
)