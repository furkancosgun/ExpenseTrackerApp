package com.furkancosgun.expensetrackerapp.presentation.screen.createexpense.scan

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.furkancosgun.expensetrackerapp.R
import com.furkancosgun.expensetrackerapp.presentation.ui.theme.ExpenseTrackerTheme
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import java.io.IOException

@Composable
fun CreateScanExpenseScreen(navController: NavController) {
    var imageUri: Any? by remember { mutableStateOf(null) }

    val context = LocalContext.current
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) {
        if (it != null) {
            Log.d("PhotoPicker", "Selected URI: $it")
            imageUri = it
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .size(250.dp)
                .clickable {
                    photoPicker.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                },
            model = ImageRequest.Builder(LocalContext.current).data(imageUri)
                .crossfade(enable = true).build(),
            contentDescription = "Avatar Image",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(24.dp))
        imageUri?.let {
            var image: InputImage? = null
            try {
                image = InputImage.fromFilePath(context, imageUri as Uri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            image?.let {
                TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS).process(it)
                    .addOnSuccessListener(OnSuccessListener { text ->
                        Toast.makeText(
                            context, text.text, Toast.LENGTH_SHORT
                        ).show()
                    }).addOnFailureListener(OnFailureListener {

                        Toast.makeText(
                            context, it.toString(), Toast.LENGTH_SHORT
                        ).show()
                        println("XFC${it.toString()}")
                    })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CreateScanExpenseScreen_Preview() {
    ExpenseTrackerTheme {
        CreateScanExpenseScreen(rememberNavController())
    }
}