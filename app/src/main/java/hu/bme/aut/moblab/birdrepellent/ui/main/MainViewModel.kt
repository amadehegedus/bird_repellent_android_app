package hu.bme.aut.moblab.birdrepellent.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.moblab.birdrepellent.model.HarmfulBird
import hu.bme.aut.moblab.birdrepellent.persistence.AppDatabase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun getAllHarmfulBirds(): LiveData<List<HarmfulBird>> {
        return repository.getAllHarmfulBirds();
    }

    fun addHarmfulBird(harmfulBird: HarmfulBird) {
        repository.addHarmfulBird(harmfulBird);
    }

    fun deleteHarmfulBird(harmfulBird: HarmfulBird) {
        repository.deleteHarmfulBird(harmfulBird);
    }
}