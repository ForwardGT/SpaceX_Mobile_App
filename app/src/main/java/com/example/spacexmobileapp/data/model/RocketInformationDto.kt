package com.example.spacexmobileapp.data.model

import com.google.gson.annotations.SerializedName

data class RocketInformationDto(

    @SerializedName("boosters") val boosters: Int,
    @SerializedName("name") val name: String,
    @SerializedName("first_flight") val firstFlight: String,
    @SerializedName("stages") val stages: Int,
    @SerializedName("flickr_images") val image: List<String>,
    @SerializedName("description") val description: String,
    @SerializedName("wikipedia") val wikipedia: String,
    @SerializedName("height") val height: HeightRocketDto,
    @SerializedName("diameter") val diameter: DiameterRocketDto
)

