package com.example.spacexmobileapp.data.model

import com.google.gson.annotations.SerializedName

data class CrewInformationDto(
    @SerializedName("name") val name: String,
    @SerializedName("agency") val agency: String,
    @SerializedName("image") val image: String,
    @SerializedName("wikipedia") val wikipedia: String,
    @SerializedName("status") val status: String,
)
