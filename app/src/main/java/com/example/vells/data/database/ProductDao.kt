package com.example.vells.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.vells.data.model.ProductPOJO
import com.example.vells.data.model.TablePOJO

@Dao
interface ProductDao {

    @Insert
    fun insert(product: ProductEntity): Long

    @Query("SELECT * FROM products_inventory ORDER BY id ASC ")
    fun getAllProducts(): List<ProductPOJO>

    @Query("DELETE FROM products_inventory WHERE id = :id")
    fun deleteProduct(id: Int)

    @Query("SELECT * FROM products_inventory WHERE name = :name")
    fun foundProduct(name: String): ProductPOJO
}