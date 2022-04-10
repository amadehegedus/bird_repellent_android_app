package hu.bme.aut.moblab.birdrepellent.dto

import com.squareup.moshi.JsonClass
import java.sql.Timestamp

@JsonClass(generateAdapter = true)
data class HarmfulBirdDto(
    var id: Long,
    var name: String,
    var enemies: List<String>,
    var active: Boolean,
    var modified: Timestamp,
)