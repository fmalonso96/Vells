package com.example.vells.ui.products.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.vells.R
import com.example.vells.data.database.DbRoom
import com.example.vells.data.model.Communicator
import com.example.vells.data.model.ProductPOJO
import com.example.vells.databinding.FragmentNewProductBinding
import com.example.vells.ui.base.ViewModelFactory
import com.example.vells.ui.products.viewmodel.ProductsViewModel
import java.lang.IllegalArgumentException

class NewProductFragment : Fragment() {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentNewProductBinding
    private lateinit var communicator: Communicator
    private lateinit var dbRoom: DbRoom

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_product, container, false)
        dbRoom = DbRoom.getDatabase(requireContext())
        communicator = activity as Communicator

        setupViewModel()
        setBtnTypeMenu()
        setBtnConfirmListener()

        return binding.root
    }

    private fun setBtnTypeMenu() {
        binding.btnTypeMenu.setOnClickListener {
            val popupMenu = PopupMenu(this.context, binding.btnTypeMenu, Gravity.END)
            popupMenu.inflate(R.menu.menu_type)
            popupMenu.show()
            popupMenu.setOnMenuItemClickListener(object: PopupMenu.OnMenuItemClickListener{

                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    when (item?.itemId) {
                        item?.itemId -> binding.tvNewProductType2.text = item?.title.toString()
                    }
                    return false
                }
            })
        }
    }

    private fun setBtnConfirmListener() {
        binding.btnNewProduct.setOnClickListener{
            var tvType = binding.tvNewProductType2.text
            val etName = binding.etNewProductName.text
            val etDescription = binding.etNewProductDescription.text
            val etPrice = binding.etNewProductPrice.text
            if (tvType.isNotEmpty() && etName.isNotEmpty() && etDescription.isNotEmpty() && etPrice.isNotEmpty()){
                val newProduct = ProductPOJO(null, etName.toString(), etDescription.toString(), etPrice.toString().toFloat())
                viewModel.insert(newProduct)
                communicator.navigateToProducts()
            }else {
                Toast.makeText(this.context, "Completar todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(dbRoom))
            .get(ProductsViewModel::class.java)
    }
}