package com.example.merchandiserappjetpackcompose.presentation.product

import com.example.merchandiserappjetpackcompose.model.Product

data class ProductState(
    val products: List<Product> = emptyList()
)