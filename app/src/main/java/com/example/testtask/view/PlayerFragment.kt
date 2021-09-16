package com.example.testtask.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.databinding.PlayerFragmentBinding
import com.example.testtask.viewmodel.PlayerViewModel
import kotlinx.coroutines.*

class PlayerFragment : Fragment(R.layout.player_fragment) {

    private lateinit var viewModel: PlayerViewModel
    private val binding by viewBinding(PlayerFragmentBinding::bind)
    private var inThisFragment = true
    private val viewCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable -> handleError(throwable) })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inThisFragment = true
        viewModel = ViewModelProvider(this).get(PlayerViewModel::class.java)
        binding.progressBar.max = App.player.duration
        setIcon()
        clicks()
        viewCoroutineScope.launch{
            setOnProgress()
        }
    }

    private fun setIcon() {
        if (App.isPlaying){
            binding.ivPlayPause.setImageResource(R.drawable.ic_pause)
        } else {
            binding.ivPlayPause.setImageResource(R.drawable.ic_play)
        }
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    private fun setOnProgress() {
        while (inThisFragment) {
            with(binding.progressBar) {
                progress = App.player.currentPosition
            }
        }
    }

    private fun clicks() {
        binding.ivCollapsePlayer.setOnClickListener {
            findNavController(this).navigate(R.id.action_playerFragment_to_mainFragment)
        }

        binding.ivPlayPause.setOnClickListener {
            App.playPause()
            setIcon()
        }

        binding.ivFfd.setOnClickListener {
            if (App.isPlaying){
                App.player.seekTo(App.player.currentPosition+5000)
            }
        }

        binding.ivBck.setOnClickListener {
            if (App.isPlaying){
                App.player.seekTo(App.player.currentPosition-5000)
            }
        }

        binding.ivMenu.setOnClickListener {
            findNavController(this).navigate(R.id.action_playerFragment_to_stagesFragment)
        }
    }

    override fun onPause() {
        super.onPause()
        viewCoroutineScope.cancel()
        inThisFragment = false
    }

    override fun onStop() {
        super.onStop()
        viewModel.setData(App.player.currentPosition)
    }
}