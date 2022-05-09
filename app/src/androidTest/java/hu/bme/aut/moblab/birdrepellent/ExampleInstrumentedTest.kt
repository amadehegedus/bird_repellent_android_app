package hu.bme.aut.moblab.birdrepellent

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import hu.bme.aut.moblab.birdrepellent.persistence.AppDatabase
import hu.bme.aut.moblab.birdrepellent.persistence.HarmfulBirdDao
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import javax.xml.transform.Source


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ExampleInstrumentedTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var harmfulBirdDao: HarmfulBirdDao

    @Before
    fun init() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        harmfulBirdDao = db.getHarmfulBirdDao()

        val harmfulBird = HarmfulBird(1, "1", mutableListOf("1"), true)
        runTest {
            harmfulBirdDao.insertHarmfulBird(harmfulBird)
        }
    }

    @After
    fun closeDb(){
        db.close()
    }
}