package hu.bme.aut.moblab.birdrepellent.di

import android.app.Application
import android.app.SharedElementCallback
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.bme.aut.moblab.birdrepellent.persistence.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application, callback: AppDatabase.Callback) = Room
        .databaseBuilder(application, AppDatabase::class.java, "app_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Provides
    fun provideHarmfulBirdDao(database: AppDatabase) = database.getHarmfulBirdDao()
}