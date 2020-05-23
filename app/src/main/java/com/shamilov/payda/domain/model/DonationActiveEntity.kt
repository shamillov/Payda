package com.shamilov.payda.domain.model

import com.shamilov.payda.data.model.Image

data class DonationActiveEntity(
    val title: String,
    val description: String,
    val amount: Int,
    val location: String,
    val progress: Int,
    val images: List<Image>
)