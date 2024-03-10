package com.example.merchandiserappjetpackcompose.ui.screen

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.merchandiserappjetpackcompose.presentation.feature.EnumPhotoCapture
import com.example.merchandiserappjetpackcompose.presentation.feature.createImageFile
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme
import java.util.Objects

@Composable
fun TakePicture(
    uriImageHandler: Uri,
    onImageCaptured: (Boolean, Uri) -> Unit
) {
    val context = LocalContext.current

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
        onImageCaptured(it, uriImageHandler)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) cameraLauncher.launch(uriImageHandler)
        else Toast.makeText(context, "Please Grant Permission", Toast.LENGTH_SHORT).show()
    }

    val permissionCheckResult = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    )

    LaunchedEffect(Unit) {
        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) cameraLauncher.launch(uriImageHandler)
        else permissionLauncher.launch(Manifest.permission.CAMERA)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ImageCaptureLogicPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            val context = LocalContext.current

            val uriImageHandler = FileProvider.getUriForFile(
                Objects.requireNonNull(context),
                context.packageName + ".provider", context.createImageFile(EnumPhotoCapture.NOT_YET_SETTED)
            )

            var capturedImageUri by remember {
                mutableStateOf<Uri>(Uri.EMPTY)
            }

            TakePicture(
                uriImageHandler,
                onImageCaptured = { isPhotoTakem, uriPhotoTaken ->
                    if (isPhotoTakem) capturedImageUri = uriPhotoTaken
                },
            )
        }
    }
}