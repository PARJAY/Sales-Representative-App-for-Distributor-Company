package com.example.merchandiserappjetpackcompose.presentation.feature

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

fun Context.createImageFile(enumPhotoCapture: EnumPhotoCapture): File {
    val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss").format(Date())

    val isOdometerPicture = "Odometer {nama_outlet} - ${timeStamp}.jpg"
    val isOutletProofVisitPicture = "Outlet {nama_outlet} - ${timeStamp}.jpg"

//    val imageFileName = "JPEG_" + timeStamp + "_"

    val image : File = File.createTempFile(
        if (enumPhotoCapture == EnumPhotoCapture.OUTLET) isOdometerPicture
        else isOutletProofVisitPicture,

        ".jpg",
        externalCacheDir
    )

    return image
}