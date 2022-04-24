package hu.bme.aut.moblab.birdrepellent.network

import hu.bme.aut.moblab.birdrepellent.network.model.xenocanto.XenoCantoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface XenoCantoApi {
    @GET("recordings")
    suspend fun getBirdSounds(
        @Query("query") xenoCantoQuery: String = "",
    ): Response<XenoCantoResponse>
}