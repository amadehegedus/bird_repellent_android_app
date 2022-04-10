package hu.bme.aut.moblab.birdrepellent.ui.details;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import hu.bme.aut.moblab.birdrepellent.model.HarmfulBird

@HiltViewModel
class DetailsViewModel @Inject
constructor(
        private val repository: DetailsRepository
) : ViewModel() {

        fun getHarmfulBirdDetails(): LiveData<HarmfulBird> {
                return repository.getHarmfulBird(1); //TODO: change 1 to real birdId
        }

        fun addEnemy(enemy: String) {
                repository.addEnemy(1, enemy); //TODO: change 1 to real birdId
        }

        fun deleteEnemy(enemy: String) {
        repository.removeEnemy(1, enemy); //TODO: change 1 to real birdId
        }
}
