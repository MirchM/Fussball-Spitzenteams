package com.mmisoft.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FootballTeam(
    val id: String? = null,
    val name: String? = null,
    val country: String? = null,
    val value: Int? = null,
    val image: String? = null,
    val european_titles: Int? = null,
    val stadium: Stadium? = null,
    val location: Location? = null,
) : Parcelable