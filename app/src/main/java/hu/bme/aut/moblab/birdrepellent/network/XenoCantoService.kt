package hu.bme.aut.moblab.birdrepellent.network

import hu.bme.aut.moblab.birdrepellent.dto.BirdSoundDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface XenoCantoService {
    @GET
    fun getBirdSoundsByName(@Query("name") birdName: String): Call<List<BirdSoundDto>>
}