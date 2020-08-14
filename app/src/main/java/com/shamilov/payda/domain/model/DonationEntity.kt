package com.shamilov.payda.domain.model

/**
 * Created by Shamilov on 22.06.2020
 */
data class DonationEntity(
    val id: Int,
    val fundId: Int,
    val title: String,
    val description: String,
    val amount: Int,
    val region: String,
    val progress: Int,
    val images: List<ImageEntity>,
    val fundLogo: String,
    val fundName: String
)