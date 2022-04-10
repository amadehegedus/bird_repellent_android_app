package hu.bme.aut.moblab.birdrepellent.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EnemyBird(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var name: String,
    var harmfulBirdId: Long,
)