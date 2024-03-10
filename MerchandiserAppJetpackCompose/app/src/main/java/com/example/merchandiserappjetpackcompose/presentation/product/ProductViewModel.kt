package com.example.merchandiserappjetpackcompose.presentation.outlet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.merchandiserappjetpackcompose.model.Product
import com.example.merchandiserappjetpackcompose.presentation.product.ProductEvent
import com.example.merchandiserappjetpackcompose.presentation.product.ProductRepository
import com.example.merchandiserappjetpackcompose.presentation.product.ProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productRepository: ProductRepository
): ViewModel() {

    private val _state = MutableStateFlow(ProductState())

    private val _product = productRepository.getAllProducts().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _product) { state, product ->
            state.copy(
                products = product
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProductState())

    fun onEvent(event: ProductEvent) {
        when(event) {
            is ProductEvent.DeleteProduct -> {
                viewModelScope.launch {
                    productRepository.deleteProduct(event.product)
                }
            }

            is ProductEvent.UpdateProduct -> {
                viewModelScope.launch {
                    productRepository.updateProduct(event.product)
                }
            }

            is ProductEvent.SaveProduct -> {
                val newOutlet = Product(productName = event.productName, productPrice = event.productPrice)
                viewModelScope.launch {
                    productRepository.insertProduct(newOutlet)
                }
            }

            else -> {}
        }
    }
}