package hu.bme.aut.moblab.birdrepellent.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BirdSoundDto (
    var download: String //URL
)