package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Shamilov on 15.07.2020
 */
data class DonationResponse (
    val amount: Int?,
    val description: String?,
    val donations: Int?,
    val fund: FundResponse,
    val id: Int?,
    val images: List<ImageResponse>,
    @SerializedName("name")
    val title: String?,
    val region: String?
)