package com.example.testtask

import android.app.Application
import android.media.MediaPlayer

class App: Application() {

    companion object{
        lateinit var player: MediaPlayer
        var isPlaying = false
        private var currentPosition = 0

       fun playPause(){
           isPlaying = if (!isPlaying){
               player.start()
               player.seekTo(currentPosition)
               true
           } else {
               player.pause()
               false
           }
        }
    }
}