package com.shamilov.payda.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FileEntity(
    val name: String,
    val size: Long,
    val contentType: String
) : Parcelable