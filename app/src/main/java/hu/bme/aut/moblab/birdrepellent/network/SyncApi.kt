package hu.bme.aut.moblab.birdrepellent.network

import hu.bme.aut.moblab.birdrepellent.network.model.sync.*
import retrofit2.Response
import retrofit2.http.*

interface SyncApi {
    @GET("sync/{id}")
    suspend fun getConfig(
        @Path("id") id: String = "",
    ): Response<List<HarmfulBirdDto>>

    @PUT("sync/{id}")
    suspend fun putConfig(
        @Path("id") id: String = "",
        @Body config: List<HarmfulBirdDto>
    ): Response<List<HarmfulBirdDto>>

    @DELETE("sync/{id}")
    suspend fun deleteConfig(
        @Path("id") id: String = "",
    )
}