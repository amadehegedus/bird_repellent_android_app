package hu.bme.aut.moblab.birdrepellent.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EnemyBird(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id", index = true)
    val id: Long,
    val name: String
)
