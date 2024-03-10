package com.example.merchandiserappjetpackcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "product")
data class LocationTracking (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val latitude: Float,
    val longitude: Float,
    val time : Date
)