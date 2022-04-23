package hu.bme.aut.moblab.birdrepellent.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HarmfulBird(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var active: Boolean,
    var modified: Long
)