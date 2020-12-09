package com.shamilov.payda.data.model.response

/**
 * Created by Shamilov on 22.06.2020
 */
data class FundResponse(
    var id: Int?,
    var name: String?,
    var description: String?,
    var logo: FileResponse?,
    var region: String?,
    var background: FileResponse?,
    var paymentSystemId: Int?,
    var users: List<UserResponse>?
)