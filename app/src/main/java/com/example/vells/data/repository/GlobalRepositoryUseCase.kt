package com.example.vells.data.repository

import com.example.vells.data.database.DbRoom
import com.example.vells.data.database.ProductEntity
import com.example.vells.data.database.TableEntity
import com.example.vells.data.model.ProductPOJO
import com.example.vells.data.model.TablePOJO

class GlobalRepositoryUseCase(private val dbRoom: DbRoom): GlobalRepository.tablesRepository, GlobalRepository.productsRepository {

    override fun insertTable(table: TablePOJO): Long {
        val dbTable = TableEntity(table.id, table.name)

        return dbRoom.tableDao.insert(dbTable)
    }

    override fun getTables(): List<TablePOJO> {
        val dbTables = dbRoom.tableDao.getAllTables()
        return dbTables
    }

    override fun deleteTable(id: Int) {
        dbRoom.tableDao.deleteTable(id)
    }

    override fun findTable(id: Int): Int {
        val table = dbRoom.tableDao.findById(id)
        return table
    }

    override fun insertProduct(product: ProductPOJO): Long {
        val dbProduct = ProductEntity(null, product.name, product.description, product.price)
        return dbRoom.productDao.insert(dbProduct)
    }

    override fun getProducts(): List<ProductPOJO> {
        val dbProduct = dbRoom.productDao.getAllProducts()
        return dbProduct
    }

    override fun deleteProduct(id: Int) {
        dbRoom.productDao.deleteProduct(id)
    }

    override fun foundProduct(name: String) {
        dbRoom.productDao.foundProduct(name)
    }
}