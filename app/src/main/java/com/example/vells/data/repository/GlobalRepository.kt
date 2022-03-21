package com.example.vells.data.repository

import com.example.vells.data.model.ProductPOJO
import com.example.vells.data.model.TablePOJO

interface GlobalRepository {

    interface tablesRepository {
        fun insertTable(table: TablePOJO): Long
        fun getTables(): List<TablePOJO>
        fun deleteTable(id: Int)
        fun findTable(id: Int): Int
    }

    interface productsRepository {
        fun insertProduct(product: ProductPOJO): Long
        fun getProducts(): List<ProductPOJO>
        fun deleteProduct(id: Int)
        fun foundProduct(name: String)
    }
}