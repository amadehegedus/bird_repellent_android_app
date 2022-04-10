package hu.bme.aut.moblab.birdrepellent.model

data class HarmfulBird(
    val name: String,
    val enemies: List<EnemyBird>
)