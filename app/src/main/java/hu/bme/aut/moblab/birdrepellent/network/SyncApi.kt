package hu.bme.aut.moblab.birdrepellent.network

import hu.bme.aut.moblab.birdrepellent.network.model.sync.DeleteSyncDto
import hu.bme.aut.moblab.birdrepellent.network.model.sync.GetSyncDto
import hu.bme.aut.moblab.birdrepellent.network.model.sync.PostSyncDto
import hu.bme.aut.moblab.birdrepellent.network.model.sync.PutSyncDto
import retrofit2.Response
import retrofit2.http.*

interface SyncApi {
    @GET("config/{requestId}")
    suspend fun getConfig(
        @Path("requestId") requestId: String = "",
    ): Response<GetSyncDto>

    @POST("config")
    suspend fun postConfig(
        @Body config: PostSyncDto
    ): Response<GetSyncDto>

    @PUT("config")
    suspend fun putConfig(
        @Body config: PutSyncDto
    ): Response<GetSyncDto>

    @DELETE("config")
    suspend fun deleteConfig(
        @Body config: DeleteSyncDto
    ): Response<GetSyncDto>
}