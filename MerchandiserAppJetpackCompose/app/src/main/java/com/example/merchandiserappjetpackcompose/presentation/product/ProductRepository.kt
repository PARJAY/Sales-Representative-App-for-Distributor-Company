package com.example.merchandiserappjetpackcompose.presentation.product

import com.example.merchandiserappjetpackcompose.data.database.ProductDao
import com.example.merchandiserappjetpackcompose.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val dao: ProductDao) {

    fun getAllProducts(): Flow<List<Product>> = dao.getAllProducts()

    suspend fun insertProduct(outlet: Product) {
        dao.insert(outlet)
    }

    suspend fun updateProduct(outlet: Product) {
        dao.update(outlet)
    }

    suspend fun deleteProduct(outlet: Product) {
        dao.delete(outlet)
    }
}