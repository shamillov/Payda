package com.shamilov.payda.domain.model

/**
 * Created by Shamilov on 22.06.2020
 */
data class FundEntity(
    val id: Int,
    val logo: FileEntity,
    val background: FileEntity,
    val name: String,
    val region: String
)