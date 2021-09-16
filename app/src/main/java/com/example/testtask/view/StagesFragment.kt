package com.example.testtask.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.testtask.R
import com.example.testtask.databinding.StagesFragmentBinding
import com.example.testtask.view.adapters.StagesAdapter
import com.example.testtask.viewmodel.StagesViewModel

class StagesFragment : Fragment(R.layout.stages_fragment) {

    private lateinit var viewModel: StagesViewModel
    private val binding by viewBinding(StagesFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StagesViewModel::class.java)
        clicks()
        setAdapter()
    }

    private fun setAdapter() {
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            binding.rvStages.adapter = StagesAdapter(it)
        })
    }

    private fun clicks() {
        binding.ivCollapse.setOnClickListener {
            findNavController(this).navigate(R.id.action_stagesFragment_to_playerFragment)
        }
    }
}