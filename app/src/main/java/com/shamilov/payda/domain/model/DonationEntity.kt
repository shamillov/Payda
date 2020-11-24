package com.shamilov.payda.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Shamilov on 22.06.2020
 */
@Parcelize
data class DonationEntity(
    val id: Int,
    val fundId: Int,
    val title: String,
    val description: String,
    var amount: String,
    val region: String,
    val progress: String,
//    val images: List<ImageEntity>,
    val files: List<FileEntity>,
    val fundLogo: FileEntity,
    val fundName: String,
    var isFavorite: Boolean = false
) : Parcelable