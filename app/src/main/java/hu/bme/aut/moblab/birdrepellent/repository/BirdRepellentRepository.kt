package hu.bme.aut.moblab.birdrepellent.repository

import hu.bme.aut.moblab.birdrepellent.network.XenoCantoApi
import hu.bme.aut.moblab.birdrepellent.util.XENO_CANTO_BASE_URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BirdRepellentRepository @Inject constructor(
    private val xenoCantoApi: XenoCantoApi
){
    suspend fun fetchBirdSounds() = xenoCantoApi.getBirdSounds("cnt:brazil")
}