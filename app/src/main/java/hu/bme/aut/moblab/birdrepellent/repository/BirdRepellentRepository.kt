package hu.bme.aut.moblab.birdrepellent.repository

import hu.bme.aut.moblab.birdrepellent.network.SyncApi
import hu.bme.aut.moblab.birdrepellent.network.XenoCantoApi
import hu.bme.aut.moblab.birdrepellent.network.model.sync.DeleteSyncDto
import hu.bme.aut.moblab.birdrepellent.network.model.sync.GetSyncDto
import hu.bme.aut.moblab.birdrepellent.network.model.sync.PostSyncDto
import hu.bme.aut.moblab.birdrepellent.network.model.sync.PutSyncDto
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
    suspend fun fetchBirdSounds() = xenoCantoApi.getBirdSounds("cnt:brazil")

    //Local DB
    suspend fun getHarmfulBirds() = harmfulBirdDao.getHarmfulBirds()

    suspend fun insertHarmfulBird(harmfulBird: HarmfulBird) = harmfulBirdDao.insertHarmfulBird(harmfulBird)

    suspend fun updateHarmfulBird(harmfulBird: HarmfulBird) = harmfulBirdDao.updateHarmfulBird(harmfulBird)

    suspend fun deleteHarmfulBird(harmfulBird: HarmfulBird) = harmfulBirdDao.deleteHarmfulBird(harmfulBird)

    suspend fun deleteAllHarmfulBirds() = harmfulBirdDao.deleteAllHarmfulBirds()

    //Sync server
    suspend fun createConfigOnServer(dto: PostSyncDto) = syncApi.postConfig(dto)

    suspend fun updateConfigOnServer(dto: PutSyncDto) = syncApi.putConfig(dto)

    suspend fun deleteConfigOnServer(dto: DeleteSyncDto) = syncApi.deleteConfig(dto)

    suspend fun fetchConfig(requestId: String) = syncApi.getConfig(requestId)

}