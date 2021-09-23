package com.example.vells.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vells.data.model.TablePOJO

@Dao
interface TableDao {

    @Insert
    fun insert(tables: TableEntity): Long

    @Query("SELECT * FROM tables ORDER BY id ASC ")
    fun getAllTables(): List<TablePOJO>

    @Query("DELETE FROM tables WHERE id = :id")
    fun deleteTable(id: Int)

    @Query("SELECT count(id) FROM tables WHERE id = :id")
    fun findById(id: Int): Int
}