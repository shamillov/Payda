package com.shamilov.payda.data.model.response

/**
 * Created by Shamilov on 22.06.2020
 */
data class FundResponse (
    var bg: String?,
    var bgContentType: String?,
    var description: String?,
    var id: Int?,
    var logo: String?,
    var logoContentType: String?,
    var name: String?,
    var paymentSystemId: Int?,
    var region: String?,
    var users: List<UserResponse>?
)