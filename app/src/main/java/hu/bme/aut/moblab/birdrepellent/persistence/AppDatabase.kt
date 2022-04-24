package hu.bme.aut.moblab.birdrepellent.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hu.bme.aut.moblab.birdrepellent.di.ApplicationScope
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [HarmfulBird::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getHarmfulBirdDao(): HarmfulBirdDao

    class Callback @Inject constructor(
        private val database: Provider<AppDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ): RoomDatabase.Callback()
}