package hu.bme.aut.moblab.birdrepellent.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hu.bme.aut.moblab.birdrepellent.model.EnemyBird
import hu.bme.aut.moblab.birdrepellent.model.HarmfulBird

@Database(entities = [HarmfulBird::class, EnemyBird::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun harmfulBirdDao(): HarmfulBirdDao;

    companion object {
        @Volatile
        private var instance: AppDatabase? = null;

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "bird_repellent.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}