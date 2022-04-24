package hu.bme.aut.moblab.birdrepellent.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "harmful_bird")
data class HarmfulBird(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var name: String = "",
    var enemies: List<String> = listOf(),
    var active: Boolean = true
)