package com.hereliesaz.all24.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hereliesaz.all24.data.model.VenueEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VenueDao {
    @Query("SELECT * FROM venues ORDER BY rank ASC")
    fun getAllVenues(): Flow<List<VenueEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenues(venues: List<VenueEntity>)

    @Query("DELETE FROM venues")
    suspend fun deleteAllVenues()
}
