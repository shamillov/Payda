package com.shamilov.payda.data.model.response

/**
 * Created by Shamilov on 22.06.2020
 */
data class FundResponse(


    var description: String?,
    var id: Int?,
    var logo: FileResponce?,
    var background: FileResponce?,
    var name: String?,
    var paymentSystemId: Int?,
    var region: String?,
    var users: List<UserResponse>?
)