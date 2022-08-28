package com.example.vells.ui.home.view

import android.os.Bundle
import android.os.Handler
import android.view.*
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
import com.example.vells.data.model.TablePOJO
import com.example.vells.databinding.FragmentHomeBinding
import com.example.vells.databinding.FragmentProductsBinding
import com.example.vells.ui.base.ViewModelFactory
import com.example.vells.ui.home.adapter.HomeAdapter
import com.example.vells.ui.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.app_bar_main.toolbarMain
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dbRoom: DbRoom
    private lateinit var communicator: Communicator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        dbRoom = DbRoom.getDatabase(requireContext())
        communicator = activity as Communicator

        setupViewModel()
        setupUI()
        setupObserver()
        setBtnListener()

        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(dbRoom))
            .get(HomeViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerViewHome.layoutManager = LinearLayoutManager(this.context, VERTICAL, false)
        viewModel.getTables()
    }

    private fun setupObserver() {

        viewModel.currentTables.observe(this.viewLifecycleOwner) {
            val adapter = HomeAdapter(it, onClick = {
                communicator.navigateToTable()
            }, object : HomeAdapter.OptionsMenuClickListener {

                override fun onOptionsMenuClicked(position: Int) {
                    performOptionsMenuClick(position)
                }
            })
            binding.recyclerViewHome.adapter = adapter
        }
    }

    private fun performOptionsMenuClick(position: Int) {
        val popupMenu = PopupMenu(this.context,binding.recyclerViewHome[position], Gravity.END)
        popupMenu.inflate(R.menu.pop_up_menu)
        popupMenu.show()
        popupMenu.setOnMenuItemClickListener(object: PopupMenu.OnMenuItemClickListener{

            override fun onMenuItemClick(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.delete -> {
                        val table = viewModel.currentTables.value?.get(position)
                        viewModel.deleteTableAndUpdate(table!!.id)
                    }
                    R.id.edit -> {
                        return true
                    }
                }
                return false
            }
        })
    }

    private fun setBtnListener() {
        binding.btnHomeFragment.setOnClickListener {
            val text = binding.etHomeFragment.text
            if(text.isNotEmpty()){
                val id = binding.etHomeFragment.text.toString().toInt()
                val newTable = TablePOJO(id, "Mesa $id")
                viewModel.insert(newTable)
                binding.etHomeFragment.text.clear()
                binding.etHomeFragment.clearFocus()
            }
        }
    }
}