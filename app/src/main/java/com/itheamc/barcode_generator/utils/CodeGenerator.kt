package com.itheamc.barcode_generator.utils

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException

class CodeGenerator {

    companion object {
        fun generate(value: String, format: BarcodeFormat): Bitmap {
            val width = 800
            val height = 800
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val codeWriter = MultiFormatWriter()
            try {
                val bitMatrix = codeWriter.encode(
                    value,
                    format,
                    width,
                    height
                )
                for (x in 0 until width) {
                    for (y in 0 until height) {
                        val color =
                            if (bitMatrix[x, y]) Color.Black.toArgb() else Color.White.toArgb()
                        bitmap.setPixel(x, y, color)
                    }
                }
            } catch (e: WriterException) {
                Log.e(TAG, "generateBarcode: ", e.cause);
            }
            return bitmap
        }
    }
}


private const val TAG = "CodeGenerator"