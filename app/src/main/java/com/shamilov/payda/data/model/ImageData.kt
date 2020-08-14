package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName


data class ImageData (
    @SerializedName("feeId")
    var feeId: Int,

    @SerializedName("id")
    var id: Int,

    @SerializedName("image")
    var image: String,

    @SerializedName("imageContentType")
    var imageContentType: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("size")
    var size: Int
)