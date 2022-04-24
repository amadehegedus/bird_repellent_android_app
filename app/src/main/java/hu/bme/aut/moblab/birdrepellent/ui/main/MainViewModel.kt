package hu.bme.aut.moblab.birdrepellent.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import hu.bme.aut.moblab.birdrepellent.network.model.xenocanto.XenoCantoResponse
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird
import hu.bme.aut.moblab.birdrepellent.repository.BirdRepellentRepository
import hu.bme.aut.moblab.birdrepellent.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject
import hu.bme.aut.moblab.birdrepellent.util.CheckInternet.Companion.internetOk
import retrofit2.Response
import java.io.IOException

@HiltViewModel
class MainViewModel @Inject constructor(
    private val birdRepellentRepository: BirdRepellentRepository,
    @ApplicationContext private val context: Context
): ViewModel() {
    public val birdSounds: MutableLiveData<Resource<XenoCantoResponse>> = MutableLiveData()
    public var xenoCantoResponse: XenoCantoResponse? = null;
    public var harmfulBirds: LiveData<List<HarmfulBird>> = MutableLiveData()

    init {
        //loadBirdSounds()
        loadHarmfulBirds()
    }

    public fun loadBirdSounds() = viewModelScope.launch { fetchBirdSounds() }

    public fun loadHarmfulBirds() = viewModelScope.launch { getHarmfulBirds() }

    private suspend fun fetchBirdSounds(){
        birdSounds.postValue(Resource.Loading())
        try {
            if (internetOk(context)){
                val response = birdRepellentRepository.fetchBirdSounds()
                birdSounds.postValue(handleXenoCantoResponse(response))
            }
            else{
                birdSounds.postValue(Resource.Error("Internet connection error"))
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

    private suspend fun getHarmfulBirds(){
        harmfulBirds = birdRepellentRepository.getHarmfulBirds()
    }
}