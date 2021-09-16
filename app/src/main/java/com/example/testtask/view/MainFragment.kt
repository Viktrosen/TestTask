package com.example.testtask.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtask.App
import com.example.testtask.R
import com.example.testtask.databinding.MainFragmentBinding
import com.example.testtask.view.adapters.ViewPagerAdapter
import com.example.testtask.viewmodel.MainViewModel
import kotlinx.coroutines.*
import java.lang.Runnable


class MainFragment : Fragment(R.layout.main_fragment), Runnable {

    private lateinit var viewModel: MainViewModel
    private val binding by viewBinding(MainFragmentBinding::bind)
    private var inThisFragment = true
    private val mImageIds = listOf(
        "https://app.surprizeme.ru/media/store/1186_i1KaYnj_8DuYTzc.jpg",
        "https://www.putivodi.ru/upload/medialibrary/87c/Зимний-дворец.jpg",
        "https://avatars.mds.yandex.net/get-zen_doc/3630671/pub_601d44ea2153d32aecdf1350_601d4e06f2a56f0eaa08387b/scale_1200",
        "https://regnum.ru/uploads/pictures/news/2016/12/29/regnum_picture_14829745761004381_normal.jpg",
        "https://7d9e88a8-f178-4098-bea5-48d960920605.selcdn.net/a8a61635-577a-48e1-ac35-fa9a584dcc02/",
        "https://grandgames.net/puzzle/source/zimniy_dvorets_3.jpg")
    private val viewCoroutineScope = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable -> handleError(throwable) })

    private fun handleError(throwable: Throwable) {
        throwable.printStackTrace()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.seekBar.max = App.player.duration
        clicks()
        setIcon()
        viewCoroutineScope.launch{
            setOnProgress()
        }
        val viewPagerAdapter = this.context?.let { ViewPagerAdapter(it, mImageIds) }
        binding.viewPager.adapter = viewPagerAdapter
    }

    private fun clicks(){
        binding.clBottomPlayer.setOnClickListener {
            findNavController(this).navigate(R.id.action_mainFragment_to_playerFragment)
        }
        binding.ivPlay.setOnClickListener {
            App.playPause()
            setIcon()
        }

        binding.ivFvd.setOnClickListener {
            if (App.isPlaying){
                App.player.seekTo(App.player.currentPosition+5000)
            }
        }

        binding.ivBack.setOnClickListener {
            if (App.isPlaying){
                App.player.seekTo(App.player.currentPosition-5000)
            }
        }
    }

    private fun setIcon() {
        if (App.isPlaying){
            binding.ivPlay.setImageResource(R.drawable.ic_pause)
        } else {
            binding.ivPlay.setImageResource(R.drawable.ic_play)
        }
    }

    override fun onPause() {
        super.onPause()
        viewCoroutineScope.cancel()
        inThisFragment = false
    }


    private fun setOnProgress() {
        with(binding.seekBar) {
            while (inThisFragment) {
                progress = App.player.currentPosition
            }
        }
    }

    override fun run() {

    }
}