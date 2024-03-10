package com.example.merchandiserappjetpackcompose.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromProductList(productList: List<ProductSold>): String {
        return gson.toJson(productList)
    }

    @TypeConverter
    fun toProductList(productListString: String): List<ProductSold> {
        val listType = object : TypeToken<List<ProductSold>>() {}.type
        return gson.fromJson(productListString, listType)
    }
}