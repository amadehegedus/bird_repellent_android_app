package hu.bme.aut.moblab.birdrepellent.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird

@Dao
interface HarmfulBirdDao {
    @Query("SELECT * FROM harmful_bird")
    fun getHarmfulBirds(): LiveData<List<HarmfulBird>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHarmfulBird(harmfulBird: HarmfulBird): Long

    @Update
    fun updateHarmfulBird(harmfulBird: HarmfulBird)

    @Delete
    fun deleteHarmfulBird(harmfulBird: HarmfulBird)

    @Query("DELETE FROM harmful_bird")
    fun deleteAllHarmfulBirds()
}