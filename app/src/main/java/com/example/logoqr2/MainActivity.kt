package com.example.logoqr2

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.alexzhirkevich.customqrgenerator.QrData
import com.github.alexzhirkevich.customqrgenerator.QrErrorCorrectionLevel
import com.github.alexzhirkevich.customqrgenerator.vector.QrCodeDrawable
import com.github.alexzhirkevich.customqrgenerator.vector.createQrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorFrameShape
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorLogoPadding
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorLogoShape

class MainActivity : AppCompatActivity() {

    private lateinit var qrImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qrImageView = findViewById(R.id.qr_image_view)

        displayQR()
    }

    private fun displayQR() {
        val data = QrData.Url("https://long-enough-link-123456789-example.com")

        val options = createQrVectorOptions {

            logo {
                drawable = ContextCompat
                    .getDrawable(this@MainActivity, R.mipmap.ic_launcher)
                size = .3f
                padding = QrVectorLogoPadding.Natural(.15f)
                shape = QrVectorLogoShape
                    .Circle
            }
            // FIXME On Android API 22 qr code with rounded corners is not scannable
//            shapes {
//                frame = QrVectorFrameShape
//                    .RoundCorners(.15f)
//            }
            errorCorrectionLevel = QrErrorCorrectionLevel.High
        }

        val drawable: Drawable = QrCodeDrawable(data, options)

        qrImageView.setImageDrawable(drawable)
    }
}