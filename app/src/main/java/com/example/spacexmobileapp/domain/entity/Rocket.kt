package com.example.spacexmobileapp.domain.entity

data class Rocket(
    val name: String,
    val boosters: Int,
    val description: String,
    val firstFlight: String,
    val stages: Int,
    val image: String,
    val wikipedia: String,
    val height: Float,
    val diameter: Float
)