package com.hereliesaz.all24.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded

@Entity(tableName = "venues")
data class VenueEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val rank: Int,
    val summary: String,
    val all24Take: String,
    @Embedded val vitals: Vitals
)

fun Venue.toEntity() = VenueEntity(
    id = id,
    name = name,
    rank = rank,
    summary = summary,
    all24Take = all24Take,
    vitals = vitals
)

fun VenueEntity.toDomain() = Venue(
    id = id,
    name = name,
    rank = rank,
    summary = summary,
    all24Take = all24Take,
    vitals = vitals
)
