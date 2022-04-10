package hu.bme.aut.moblab.birdrepellent.ui.main

import androidx.lifecycle.LiveData
import hu.bme.aut.moblab.birdrepellent.model.HarmfulBird
import hu.bme.aut.moblab.birdrepellent.network.SyncService
import hu.bme.aut.moblab.birdrepellent.persistence.HarmfulBirdDao

class MainRepository(private val harmfulBirdDao: HarmfulBirdDao, private val syncService: SyncService) {
    fun getAllHarmfulBirds(): LiveData<List<HarmfulBird>> {
        return harmfulBirdDao.getAll();
    }

    fun addHarmfulBird(harmfulBird: HarmfulBird) {
       harmfulBirdDao.insert(harmfulBird);
    }

    fun deleteHarmfulBird(harmfulBird: HarmfulBird) {
        harmfulBirdDao.delete(harmfulBird);
    }
}