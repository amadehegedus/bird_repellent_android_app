package hu.bme.aut.moblab.birdrepellent.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.moblab.birdrepellent.util.Resource

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.birdSounds.observe(this) {
            when(it){
                is Resource.Success -> {
                    it.data?.let { response ->
                        Toast.makeText(applicationContext, response.numRecordings, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Error -> {
                    it.message?.let { message ->
                        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                        Log.e("MainActivity", "Error: $message")
                    }
                }
                is Resource.Loading -> {
                    //TODO LOADING
                }
            }
        }
    }
}