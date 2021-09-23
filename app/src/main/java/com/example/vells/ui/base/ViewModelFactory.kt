package com.example.vells.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vells.data.database.DbRoom
import com.example.vells.data.database.TableDao
import com.example.vells.data.repository.MainRepository
import com.example.vells.ui.home.viewmodel.HomeViewModel
import com.example.vells.ui.products.viewmodel.ProductsViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val dbRoom: DbRoom): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (modelClass) {
            HomeViewModel::class.java -> HomeViewModel(MainRepository(dbRoom)) as T
            ProductsViewModel::class.java -> ProductsViewModel(MainRepository(dbRoom)) as T
            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}