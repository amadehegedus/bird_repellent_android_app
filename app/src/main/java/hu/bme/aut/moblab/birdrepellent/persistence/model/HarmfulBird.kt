package hu.bme.aut.moblab.birdrepellent.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "harmful_bird")
data class HarmfulBird(
    @PrimaryKey(autoGenerate = true)
    var id : Long?,
    var name: String = "",
    var enemies: MutableList<String> = arrayListOf<String>(),
    var active: Boolean = true
)