package com.example.vells.ui.products.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vells.data.model.ProductPOJO
import com.example.vells.data.model.TablePOJO
import com.example.vells.data.repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel(private val mainRepository: MainRepository): ViewModel() {

    private val products = MutableLiveData<List<ProductPOJO>>()
    val currentProducts: LiveData<List<ProductPOJO>>
        get() = products

    fun insert(productPOJO: ProductPOJO){
        CoroutineScope(Dispatchers.IO).launch {
            val id = mainRepository.insertProduct(productPOJO)
            if (id > 0){
                getProducts()
            }
        }
    }
    
    fun getProducts(){
        CoroutineScope(Dispatchers.IO).launch {
            val product = mainRepository.getProducts()
            products.postValue(product)
        }
    }

    fun deleteProduct(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            mainRepository.deleteProduct(id)
        }
    }
}