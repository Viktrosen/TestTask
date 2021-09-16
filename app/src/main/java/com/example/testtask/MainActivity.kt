package com.example.testtask

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.player = MediaPlayer.create(this, R.raw.aces_high)
    }

    override fun onPause() {
        super.onPause()
        App.player.release()
    }
}