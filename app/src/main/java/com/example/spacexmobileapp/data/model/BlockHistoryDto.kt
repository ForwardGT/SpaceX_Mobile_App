package com.example.spacexmobileapp.data.model

import com.google.gson.annotations.SerializedName

data class BlockHistoryDto(
    @SerializedName("title") val title: String,
    @SerializedName("event_date_unix") val eventDate: Long,
    @SerializedName("details") val details: String
)
