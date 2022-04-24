package hu.bme.aut.moblab.birdrepellent.network.model.sync

data class HarmfulBirdDto (
    var id : Long,
    var name: String = "",
    var enemies: List<String> = listOf(),
    var active: Boolean = true
)

data class GetSyncDto(
    var requestId: String = "",
    var harmfulBirds: List<HarmfulBirdDto> = listOf()
)

data class PostSyncDto(
    var adminId: String = "",
    var requestId: String = "",
    var harmfulBirds: List<HarmfulBirdDto> = listOf()
)

data class PutSyncDto(
    var adminId: String = "",
    var requestId: String = "",
    var harmfulBirds: List<HarmfulBirdDto> = listOf()
)

data class DeleteSyncDto(
    var adminId: String = "",
    var requestId: String = "",
    var harmfulBirds: List<HarmfulBirdDto> = listOf()
)