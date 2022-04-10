package hu.bme.aut.moblab.birdrepellent.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.bme.aut.moblab.birdrepellent.model.HarmfulBird

@Dao
interface HarmfulBirdDao {
    @Query("SELECT * FROM harmfulbird WHERE id = :id")
    fun getOne(id: Long): LiveData<HarmfulBird>;
    @Query("SELECT * FROM harmfulbird")
    fun getAll(): LiveData<List<HarmfulBird>>;
    @Insert
    fun insert(harmfulBird: HarmfulBird): Long;
    @Delete
    fun delete(harmfulBird: HarmfulBird);
    @Update
    fun update(harmfulBird: HarmfulBird);
}