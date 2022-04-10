package hu.bme.aut.moblab.birdrepellent.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import hu.bme.aut.moblab.birdrepellent.model.EnemyBird

@Dao
interface EnemyBirdDao {
    fun getOne(id: Long): LiveData<EnemyBird>;
    fun getAll(): LiveData<List<EnemyBird>>;
    fun insert(enemyBird: EnemyBird): Long;
    fun delete(enemyBird: EnemyBird);
    fun deleteAll();
    fun update(enemyBird: EnemyBird);
}