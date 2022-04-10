package hu.bme.aut.moblab.birdrepellent.network

import hu.bme.aut.moblab.birdrepellent.dto.HarmfulBirdDto
import retrofit2.Call
import retrofit2.http.*

interface SyncService {
    @PUT
    fun putHarmfulBirds(@Query("deviceId") deviceId: Long, @Body harmfulBirds: List<HarmfulBirdDto>): Call<List<HarmfulBirdDto>>

    @PUT
    fun putHarmfulBird(@Query("deviceId") deviceId: Long, @Body harmfulBird: HarmfulBirdDto): Call<HarmfulBirdDto>

    @GET
    fun getAllHarmfulBirds(@Query("deviceId") deviceId: Long);

    @DELETE
    fun deleteHarmfulBird(@Query("deviceId") deviceId: Long, @Body harmfulBird: HarmfulBirdDto);

    @DELETE
    fun deleteAllHarmfulBirds(@Query("deviceId") deviceId: Long);
}