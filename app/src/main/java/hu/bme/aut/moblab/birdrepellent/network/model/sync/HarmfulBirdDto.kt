package hu.bme.aut.moblab.birdrepellent.network.model.sync

data class HarmfulBirdDto (
    var id : Long?,
    var name: String? = "",
    var enemies: List<String>? = listOf(),
    var active: Boolean? = true
)