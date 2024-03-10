package com.example.merchandiserappjetpackcompose.presentation.product

import com.example.merchandiserappjetpackcompose.model.Product

sealed interface ProductEvent {
    data class SaveProduct(val productName: String, val productPrice: Double): ProductEvent
    data class UpdateProduct(val product: Product): ProductEvent
    data class DeleteProduct(val product: Product): ProductEvent
}