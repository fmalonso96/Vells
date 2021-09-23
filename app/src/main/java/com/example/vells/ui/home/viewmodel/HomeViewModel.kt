package com.example.vells.ui.home.viewmodel

import android.app.Application
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vells.data.database.TableDao
import com.example.vells.data.database.TableEntity
import com.example.vells.data.model.TablePOJO
import com.example.vells.data.repository.MainRepository
import kotlinx.coroutines.*

class HomeViewModel(private val mainRepository: MainRepository): ViewModel() {

    private val tables = MutableLiveData<List<TablePOJO>>()
    val currentTables: LiveData<List<TablePOJO>>
        get() = tables

    fun insert(tablePOJO: TablePOJO){
        CoroutineScope(Dispatchers.IO).launch {
            val id = mainRepository.insertTable(tablePOJO)
            if (id > 0){
                getTables()
            }
        }
    }

    fun getTables(){
        CoroutineScope(Dispatchers.IO).launch {
            val table = mainRepository.getTables()
            tables.postValue(table)
        }
    }

    fun deleteTable(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            mainRepository.deleteTable(id)
        }
    }
}