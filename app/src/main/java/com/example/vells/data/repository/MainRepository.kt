package com.example.vells.data.repository

import com.example.vells.data.database.DbRoom
import com.example.vells.data.database.ProductEntity
import com.example.vells.data.database.TableEntity
import com.example.vells.data.model.ProductPOJO
import com.example.vells.data.model.TablePOJO

class MainRepository(private val dbRoom: DbRoom) {

    fun insertTable(table: TablePOJO): Long {
        val dbTable = TableEntity(table.id, table.name)
        return dbRoom.tableDao.insert(dbTable)
    }

    fun getTables(): List<TablePOJO> {
        val dbTables = dbRoom.tableDao.getAllTables()
        return dbTables
    }

    fun deleteTable(id: Int) {
        dbRoom.tableDao.deleteTable(id)
    }

    fun findTable(id: Int): Int {
        val table = dbRoom.tableDao.findById(id)
        return table
    }

    fun insertProduct(product: ProductPOJO): Long {
        val dbProduct = ProductEntity(null, product.name, product.description, product.price)
        return dbRoom.productDao.insert(dbProduct)
    }

    fun getProducts(): List<ProductPOJO> {
        val dbProduct = dbRoom.productDao.getAllProducts()
        return dbProduct
    }

    fun deleteProduct(id: Int) {
        dbRoom.productDao.deleteProduct(id)
    }

    fun foundProduct(name: String) {
        dbRoom.productDao.foundProduct(name)
    }
}