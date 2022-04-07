package com.mmisoft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Stadium (
    val size: Int,
    val name: String,
) : Parcelable