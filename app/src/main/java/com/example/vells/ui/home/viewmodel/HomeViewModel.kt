package com.example.vells.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vells.data.model.TablePOJO
import com.example.vells.data.repository.GlobalRepositoryUseCase
import kotlinx.coroutines.*

class HomeViewModel(private val globalRepositoryUseCase: GlobalRepositoryUseCase): ViewModel() {

    private val tables = MutableLiveData<List<TablePOJO>>()
    val currentTables: LiveData<List<TablePOJO>>
        get() = tables

    fun insert(tablePOJO: TablePOJO){
        CoroutineScope(Dispatchers.IO).launch {
            val id = globalRepositoryUseCase.insertTable(tablePOJO)
            if (id > 0){
                getTables()
            }
        }
    }

    fun getTables(){
        CoroutineScope(Dispatchers.IO).launch {
            val tablesList = globalRepositoryUseCase.getTables()
            tables.postValue(tablesList)
        }
    }

    fun deleteTableAndUpdate(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            globalRepositoryUseCase.deleteTable(id)
            getTables()
        }
    }
}