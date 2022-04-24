package hu.bme.aut.moblab.birdrepellent.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import hu.bme.aut.moblab.birdrepellent.persistence.AppDatabase
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [PersistenceModule::class]
)
object PersistenceModuleMock {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application, callback: AppDatabase.Callback) = Room
        .inMemoryDatabaseBuilder(application, AppDatabase::class.java)
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideHarmfulBirdDao(database: AppDatabase) = database.getHarmfulBirdDao()
}