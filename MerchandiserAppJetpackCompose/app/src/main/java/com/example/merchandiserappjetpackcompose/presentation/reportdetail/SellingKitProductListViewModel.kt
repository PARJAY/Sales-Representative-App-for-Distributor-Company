package com.example.merchandiserappjetpackcompose.presentation.reportdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SellingKitProductListViewModel(private val dataStore: SellingKitProductListDataStore) : ViewModel() {

    private val _sellingProductKit = MutableLiveData<List<Int>>()

    var sellingProductList: StateFlow<List<Int>?> = dataStore.getSellingItemKit().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addItem(item: Int) {
        val currentKit = sellingProductList.value ?: emptyList()
        val updatedKit = currentKit + item
        _sellingProductKit.value = updatedKit

        viewModelScope.launch {
            dataStore.saveSellingItemKit(updatedKit)
        }
    }

    fun removeAllItems() {
        viewModelScope.launch {
            dataStore.saveSellingItemKit(emptyList())
        }
    }

    fun removeItemAtIndex(index: Int) {
        val currentKit = sellingProductList.value ?: return

        if (index in currentKit.indices) {
            val updatedKit = currentKit.toMutableList()
            updatedKit.removeAt(index)
            _sellingProductKit.value = updatedKit

            viewModelScope.launch {
                dataStore.saveSellingItemKit(updatedKit)
            }
        }
    }
}