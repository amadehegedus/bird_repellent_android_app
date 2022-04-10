package hu.bme.aut.moblab.birdrepellent.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import hu.bme.aut.moblab.birdrepellent.model.HarmfulBird

@Dao
interface HarmfulBirdDao {
    fun getOne(id: Long): LiveData<HarmfulBird>;
    fun getAll(): LiveData<List<HarmfulBird>>;
    fun insert(harmfulBird: HarmfulBird): Long;
    fun delete(harmfulBird: HarmfulBird);
    fun deleteAll();
    fun update(harmfulBird: HarmfulBird);
}