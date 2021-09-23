package com.example.vells.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_inventory")
data class ProductEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int?,

    @ColumnInfo()
    var name: String,

    @ColumnInfo()
    var description: String,

    @ColumnInfo()
    var price: Float
)
