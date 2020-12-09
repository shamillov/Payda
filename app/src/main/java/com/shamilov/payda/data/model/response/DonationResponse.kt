package com.shamilov.payda.data.model.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Shamilov on 15.07.2020
 */
data class DonationResponse(
    val id: Int?,
    val amount: Int?,
    val description: String?,
    val donations: Int?,
    val fund: FundResponse,
    val files: List<FileResponse>,
    @SerializedName("name")
    val title: String?,
    val region: String?,
    val active: Boolean?
)