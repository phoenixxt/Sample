package com.example.samplereg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigation: Navigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigation.provideActivity(this)

        setContentView(R.layout.activity_main)

        navigation.openStartingScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        navigation.activityDestroyed()
    }

}
