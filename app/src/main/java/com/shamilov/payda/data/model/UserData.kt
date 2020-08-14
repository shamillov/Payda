package com.shamilov.payda.data.model

import com.google.gson.annotations.SerializedName


class UserData {
    @SerializedName("activated")
    var activated: Boolean? = null

    @SerializedName("authorities")
    var authorities: List<String>? = null

    @SerializedName("createdBy")
    var createdBy: String? = null

    @SerializedName("createdDate")
    var createdDate: String? = null

    @SerializedName("email")
    var email: String? = null

    @SerializedName("firstName")
    var firstName: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("imageUrl")
    var imageUrl: String? = null

    @SerializedName("langKey")
    var langKey: String? = null

    @SerializedName("lastModifiedBy")
    var lastModifiedBy: String? = null

    @SerializedName("lastModifiedDate")
    var lastModifiedDate: String? = null

    @SerializedName("lastName")
    var lastName: String? = null

    @SerializedName("login")
    var login: String? = null
}