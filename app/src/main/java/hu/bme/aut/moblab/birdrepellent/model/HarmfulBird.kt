package hu.bme.aut.moblab.birdrepellent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HarmfulBird(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val enemies: List<EnemyBird>,
    val active: Boolean
)