package com.romzc.secondapp.infraestructure.models

import com.google.gson.annotations.SerializedName


data class SuperHeroResponse(
    @SerializedName("response") val status: String,
    @SerializedName("results") val superHeroes: List<SuperHeroItemResponse>,
)

data class SuperHeroItemResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val superHeroName: String,
    @SerializedName("image") val superHeroImage: SuperHeroImageResponse,
    @SerializedName("powerstats") val superHeroStats: PowerStatsResponse,
    @SerializedName("biography") val biography: Biography
)

data class SuperHeroImageResponse(
    @SerializedName("url") val url: String
)

