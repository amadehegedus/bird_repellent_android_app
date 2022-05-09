package hu.bme.aut.moblab.birdrepellent.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird

@Dao
interface HarmfulBirdDao {
    @Query("SELECT * FROM harmful_bird")
    fun getHarmfulBirds(): LiveData<List<HarmfulBird>>

    @Query("SELECT * FROM harmful_bird WHERE id=:id")
    fun getHarmfulBirdById(id: Long): LiveData<HarmfulBird>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHarmfulBird(harmfulBird: HarmfulBird): Long

    @Update
    suspend fun updateHarmfulBird(harmfulBird: HarmfulBird)

    @Delete
    suspend fun deleteHarmfulBird(harmfulBird: HarmfulBird)

    @Query("DELETE FROM harmful_bird")
    suspend fun deleteAllHarmfulBirds()
}