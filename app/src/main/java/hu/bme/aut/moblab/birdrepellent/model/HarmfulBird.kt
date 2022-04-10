package hu.bme.aut.moblab.birdrepellent.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity
data class HarmfulBird(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var enemies: List<String>,
    var active: Boolean,
    var modified: Timestamp
)