package com.hereliesaz.all24.data.model

data class Venue(
    val id: Int,
    val name: String,
    val rank: Int,
    val summary: String,
    val all24Take: String,
    val vitals: Vitals
)

data class Vitals(
    val address: String,
    val hours: String,
    val phone: String
)
