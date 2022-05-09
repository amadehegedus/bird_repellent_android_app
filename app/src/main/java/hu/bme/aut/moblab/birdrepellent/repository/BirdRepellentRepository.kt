package hu.bme.aut.moblab.birdrepellent.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hu.bme.aut.moblab.birdrepellent.network.SyncApi
import hu.bme.aut.moblab.birdrepellent.network.XenoCantoApi
import hu.bme.aut.moblab.birdrepellent.network.model.sync.*
import hu.bme.aut.moblab.birdrepellent.persistence.HarmfulBirdDao
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird
import hu.bme.aut.moblab.birdrepellent.util.XENO_CANTO_BASE_URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BirdRepellentRepository @Inject constructor(
    private val xenoCantoApi: XenoCantoApi,
    private val syncApi: SyncApi,
    private val harmfulBirdDao: HarmfulBirdDao
){
    //XenoCanto (bird sounds) API
    suspend fun fetchBirdSounds(bird: String) = xenoCantoApi.getBirdSounds(bird)

    //Local DB
    fun getHarmfulBirds() = harmfulBirdDao.getHarmfulBirds()

    fun getHarmfulBirdById(id: Long) = harmfulBirdDao.getHarmfulBirdById(id)

    suspend fun insertHarmfulBird(harmfulBird: HarmfulBird) = harmfulBirdDao.insertHarmfulBird(harmfulBird)

    suspend fun updateHarmfulBird(harmfulBird: HarmfulBird) = harmfulBirdDao.updateHarmfulBird(harmfulBird)

    suspend fun deleteHarmfulBird(harmfulBird: HarmfulBird) = harmfulBirdDao.deleteHarmfulBird(harmfulBird)

    suspend fun deleteAllHarmfulBirds() = harmfulBirdDao.deleteAllHarmfulBirds()

    //Sync server

    suspend fun uploadConfig(id: String, config: List<HarmfulBirdDto>) = syncApi.putConfig(id, config)

    suspend fun deleteConfig(id: String) = syncApi.deleteConfig(id)

    suspend fun downloadConfig(id: String) = syncApi.getConfig(id)

}