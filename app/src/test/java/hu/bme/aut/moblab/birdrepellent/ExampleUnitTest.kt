package hu.bme.aut.moblab.birdrepellent

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.moblab.birdrepellent.persistence.AppDatabase
import hu.bme.aut.moblab.birdrepellent.persistence.model.HarmfulBird
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {
    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @After
    fun close() {
        db.close()
    }

    @Inject
    lateinit var db: AppDatabase

    @ExperimentalCoroutinesApi
    @Test
    fun testInsert() = runTest {
        val dao = db.getHarmfulBirdDao()
        dao.insertHarmfulBird(HarmfulBird(1, "2", mutableListOf(), true))
        assertEquals(1, dao.getHarmfulBirds().)
    }
}