package com.example.merchandiserappjetpackcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var productName: String,
    var productPrice: Double
)

// TODO : isDisable : Boolean,