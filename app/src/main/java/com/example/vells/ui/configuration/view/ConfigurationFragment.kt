package com.example.vells.ui.configuration.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.vells.R
import com.example.vells.data.model.Communicator
import com.example.vells.databinding.FragmentConfigurationBinding

class ConfigurationFragment : Fragment() {

    private lateinit var binding: FragmentConfigurationBinding
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_configuration, container, false)
        communicator = activity as Communicator

        setBtnExitListener()

        return binding.root
    }

    private fun setBtnExitListener() {
        binding.btnExit.setOnClickListener {
            communicator.navigateToHome()
        }
    }
}