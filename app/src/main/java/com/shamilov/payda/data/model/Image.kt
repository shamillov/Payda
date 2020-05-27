package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageContentType")
    val imageContentType: String,
    @SerializedName("feeId")
    val feeId: Int
)