package hu.bme.aut.moblab.birdrepellent.ui.enemybirds

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird
import hu.bme.aut.moblab.birdrepellent.repository.BirdRepellentRepository
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnemyBirdsViewModel @Inject constructor(
    private val birdRepellentRepository: BirdRepellentRepository
): ViewModel() {
    lateinit var harmfulBird: HarmfulBird

    fun removeEnemy(enemyBird: String){
        harmfulBird.enemies.remove(enemyBird)
        updateHarmfulBird()
    }

    fun addEnemy(enemyBird: String){
        harmfulBird.enemies.add(enemyBird)
        updateHarmfulBird()
    }

    private fun updateHarmfulBird(){
        viewModelScope.launch {
            birdRepellentRepository.updateHarmfulBird(harmfulBird);
        }
    }

    fun getHarmfulBirdDetails(harmfulBirdId: Long) = birdRepellentRepository.getHarmfulBirdById(harmfulBirdId)

}