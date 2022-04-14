package com.itheamc.barcode_generator

import android.graphics.Bitmap
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.zxing.BarcodeFormat
import com.itheamc.barcode_generator.ui.theme.BarcodeGeneratorTheme
import com.itheamc.barcode_generator.utils.CodeGenerator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BarcodeGeneratorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BarcodeGenerator()
                }
            }
        }
    }
}


@Composable
private fun BarcodeGenerator() {
    var bitmap: Bitmap? by rememberSaveable {
        mutableStateOf(null)
    }

    var value by rememberSaveable {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        bitmap?.let {
            Image(
                modifier = Modifier.fillMaxWidth(),
                bitmap = it.asImageBitmap(),
                contentDescription = "code",
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.requiredSize(24.dp))

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(50.dp),
            textStyle = TextStyle(
                textAlign = TextAlign.Center
            ),
            value = value,
            onValueChange = { value = it }
        )

        Spacer(modifier = Modifier.requiredSize(24.dp))

        Button(
            onClick = {
                bitmap = if (value.isNotBlank()) CodeGenerator.generate(value = value, format = BarcodeFormat.QR_CODE) else null
            }
        ) {
            Text(text = "Generate Now")
        }
    }
}