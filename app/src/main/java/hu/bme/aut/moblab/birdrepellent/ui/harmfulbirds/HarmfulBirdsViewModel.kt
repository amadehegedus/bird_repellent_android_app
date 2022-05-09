package hu.bme.aut.moblab.birdrepellent.ui.harmfulbirds

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.moblab.birdrepellent.network.model.sync.HarmfulBirdDto
import hu.bme.aut.moblab.birdrepellent.network.model.xenocanto.XenoCantoResponse
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird
import hu.bme.aut.moblab.birdrepellent.repository.BirdRepellentRepository
import hu.bme.aut.moblab.birdrepellent.util.CheckInternet.Companion.internetOk
import hu.bme.aut.moblab.birdrepellent.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HarmfulBirdsViewModel @Inject constructor(
    private val birdRepellentRepository: BirdRepellentRepository,
    @ApplicationContext private val context: Context,
    val mediaPlayer: MediaPlayer
): ViewModel() {
    var birdSounds: MutableLiveData<Resource<XenoCantoResponse>> = MutableLiveData()
    lateinit var xenoCantoResponse: XenoCantoResponse;
    var repellerState: Boolean = false;

    public fun play(url: String){
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.start()
    }

    public fun loadBirdSounds(bird: String) = viewModelScope.launch { fetchBirdSounds(bird) }

    private suspend fun fetchBirdSounds(bird: String){
        birdSounds.postValue(Resource.Loading())
        try {
            if (internetOk(context)){
                val response = birdRepellentRepository.fetchBirdSounds(bird)
                birdSounds.postValue(handleXenoCantoResponse(response))
            }
            else{
                birdSounds.postValue(Resource.Error("Connection error"))
            }
        }
        catch (ex : Exception){
            when(ex){
                is IOException -> birdSounds.postValue(Resource.Error("Network Failure"))
                else -> birdSounds.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleXenoCantoResponse(response: Response<XenoCantoResponse>): Resource<XenoCantoResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                xenoCantoResponse = resultResponse
                return Resource.Success(xenoCantoResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getHarmfulBirds() = birdRepellentRepository.getHarmfulBirds()

    fun createHarmfulBird(name: String){
        viewModelScope.launch {
            var bird: HarmfulBird = HarmfulBird(null, name, mutableListOf(), false)
            birdRepellentRepository.insertHarmfulBird(bird)
        }
    }

    fun deleteHarmfulBird(harmfulBird: HarmfulBird){
        viewModelScope.launch {
            birdRepellentRepository.deleteHarmfulBird(harmfulBird)
        }
    }

    fun updateHarmfulBird(harmfulBird: HarmfulBird){
        viewModelScope.launch {
            birdRepellentRepository.updateHarmfulBird(harmfulBird);
        }
    }

    fun uploadConfig(id: String, harmfulBirds: List<HarmfulBirdDto>){
        viewModelScope.launch {
            birdRepellentRepository.uploadConfig(id, harmfulBirds)
        }
    }

    fun downloadConfig(id: String){
        viewModelScope.launch {
            clearLocalDb()
            for (c in birdRepellentRepository.downloadConfig(id).body()!!){
                var harmfulBird = convertHarmfulBirdDtoToHarmfulBird(c)
                birdRepellentRepository.insertHarmfulBird(harmfulBird)
            }
        }
    }

    fun deleteConfig(id: String){
        viewModelScope.launch {
            birdRepellentRepository.deleteConfig(id)
        }
    }

    private fun clearLocalDb(){
        viewModelScope.launch {
            birdRepellentRepository.deleteAllHarmfulBirds()
        }
    }

    fun convertHarmfulBirdToHarmfulBirdDto(harmfulBird: HarmfulBird) = HarmfulBirdDto(harmfulBird.id, harmfulBird.name, harmfulBird.enemies, harmfulBird.active)

    fun convertHarmfulBirdDtoToHarmfulBird(harmfulBirdDto: HarmfulBirdDto) = HarmfulBird(harmfulBirdDto.id, harmfulBirdDto.name!!,
        harmfulBirdDto.enemies as MutableList<String>, harmfulBirdDto.active!!)
}