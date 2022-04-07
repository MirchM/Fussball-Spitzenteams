package com.mmisoft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location (
    val lat: Double,
    val lng: Double,
        ) : Parcelable