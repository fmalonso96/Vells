package com.example.vells.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tables")
data class TableEntity (

    @PrimaryKey()
    var id: Int,

    @ColumnInfo()
    var name: String
)