package hu.bme.aut.moblab.birdrepellent.ui.details

import androidx.lifecycle.LiveData
import hu.bme.aut.moblab.birdrepellent.model.HarmfulBird
import hu.bme.aut.moblab.birdrepellent.network.SyncService
import hu.bme.aut.moblab.birdrepellent.persistence.HarmfulBirdDao

class DetailsRepository(private val harmfulBirdDao: HarmfulBirdDao, private val syncService: SyncService) {
    fun getHarmfulBird(birdId: Long): LiveData<HarmfulBird> {
        return harmfulBirdDao.getOne(birdId);
    }

    fun addEnemy(birdId: Long, enemyName: String): LiveData<HarmfulBird>{
        var harmfulBird: LiveData<HarmfulBird> = harmfulBirdDao.getOne(birdId);
        //TODO: modify
        harmfulBirdDao.update(harmfulBird.value!!);
        return harmfulBird;
    }

    fun removeEnemy(birdId: Long, enemyName: String): LiveData<HarmfulBird>{
        var harmfulBird: LiveData<HarmfulBird> = harmfulBirdDao.getOne(birdId);
        //TODO: modify
        harmfulBirdDao.update(harmfulBird.value!!);
        return harmfulBird;
    }

    fun removeAllEnemies(birdId: Long, enemyName: String): LiveData<HarmfulBird>{
        var harmfulBird: LiveData<HarmfulBird> = harmfulBirdDao.getOne(birdId);
        //TODO: modify
        harmfulBirdDao.update(harmfulBird.value!!);
        return harmfulBird;
    }
}