package com.example.vells.ui.tables.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.vells.data.model.Communicator
import com.example.vells.databinding.FragmentTableBinding

class TableFragment() : Fragment() {

    private lateinit var binding: FragmentTableBinding
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTableBinding.inflate(inflater, container, false)
        communicator = activity as Communicator

        //setupViewModel()
        setupUI()
        //setupObserver()
        setBtnPayListener()
        //setFloatingListener()

        return binding.root
    }

    private fun setupUI() {

    }

    private fun setBtnPayListener() {
        binding.btnPay.setOnLongClickListener {
            //AGREGAR ACCION CORRECTA
            communicator.navigateToHome()
            return@setOnLongClickListener true
        }
    }
}