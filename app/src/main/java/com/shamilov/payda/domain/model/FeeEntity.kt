package com.shamilov.payda.domain.model

import com.google.gson.annotations.SerializedName
import com.shamilov.payda.data.model.Image

/**
 * Created by Shamilov on 22.06.2020
 */
data class FeeEntity(
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("region")
    val location: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("donations")
    val progress: Int,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("description")
    val description: String
)