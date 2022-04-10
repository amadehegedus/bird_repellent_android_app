package hu.bme.aut.moblab.birdrepellent.dto

import com.squareup.moshi.JsonClass
import hu.bme.aut.moblab.birdrepellent.model.EnemyBird

@JsonClass(generateAdapter = true)
data class HarmfulBirdDto(
    val id: Long,
    val name: String,
    val enemies: List<EnemyBird>,
    val active: Boolean
)