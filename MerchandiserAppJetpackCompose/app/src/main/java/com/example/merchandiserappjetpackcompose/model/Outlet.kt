package com.example.merchandiserappjetpackcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "outlet")
data class Outlet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var outletName: String
)


// TODO : outletLocationName : String,
// TODO : outletLocationLatitude : Long,
// TODO : outletLocationLongitude : Long,
// TODO : isDisable : Boolean,

// TODO : outletVersionControl : List<OutletVersionControl>,  // TODO : better be in new table