package com.example.vells.ui.products.view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.example.vells.R
import com.example.vells.data.database.DbRoom
import com.example.vells.data.model.Communicator
import com.example.vells.databinding.FragmentProductsBinding
import com.example.vells.ui.base.ViewModelFactory
import com.example.vells.ui.products.adapter.ProductsAdapter
import com.example.vells.ui.products.viewmodel.ProductsViewModel

class ProductsFragment: Fragment() {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentProductsBinding
    private lateinit var dbRoom: DbRoom
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        dbRoom = DbRoom.getDatabase(requireContext())
        communicator = activity as Communicator

        setupViewModel()
        setupUI()
        setupObserver()
        //setBtnFindListener()
        setFloatingListener()

        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(dbRoom))
            .get(ProductsViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this.context, VERTICAL, false)
        viewModel.getProducts()
    }

    private fun setupObserver() {
        viewModel.currentProducts.observe(this.viewLifecycleOwner) {
            val adapter = ProductsAdapter(it, onClick = {
                // TODO: 22/9/2021
            }, object : ProductsAdapter.OptionsMenuClickListener {

                override fun onOptionsMenuClicked(position: Int) {
                    performOptionsMenuClick(position)
                }
            })
            binding.recyclerViewProducts.adapter = adapter
        }
    }

    fun setFloatingListener() {
        binding.btnFloatingProducts.setOnClickListener {
            communicator.navigateToNewProduct()
        }
    }

    private fun performOptionsMenuClick(position: Int) {
        val popupMenu = PopupMenu(this.context,binding.recyclerViewProducts[position], Gravity.END)
        popupMenu.inflate(R.menu.pop_up_menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener(object: PopupMenu.OnMenuItemClickListener{

            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.delete -> {
                        val localProduct = viewModel.currentProducts.value?.get(position)
                        viewModel.deleteProduct(localProduct!!.id!!)
                        viewModel.getProducts()
                    }
                    R.id.edit -> {
                        return true
                    }
                }
                return false
            }
        })
    }
}