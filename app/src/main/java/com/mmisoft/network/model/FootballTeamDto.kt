package com.mmisoft.network.model

import com.mmisoft.domain.model.Location
import com.mmisoft.domain.model.Stadium
import com.google.gson.annotations.SerializedName

data class FootballTeamDto (

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("country")
    var country: String? = null,

    @SerializedName("value")
    var value: Int? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("european_titles")
    var european_titles: Int? = null,

    @SerializedName("location")
    var location: Location? = null,

    @SerializedName("stadium")
    var stadium: Stadium? = null
)