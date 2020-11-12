package com.shamilov.payda.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Shamilov on 15.07.2020
 */
@Parcelize
data class ImageEntity (
    val id: Int,
    val image: String
) : Parcelable