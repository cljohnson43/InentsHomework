package com.example.inentshomework

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_display_image.*

const val REQUEST_IMAGE_CAPTURE = 1

class DisplayImage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE -> {
                if (resultCode == Activity.RESULT_OK) {
                    val image = data?.extras?.get("data") as Bitmap
                    ivDisplayImage.setImageBitmap(image)
                }
            }

        }

        if (resultCode == Activity.RESULT_CANCELED) super.onActivityResult(requestCode, resultCode, data)
     }

    fun onClick(view: View) {
        when (view.id) {
            R.id.fab -> dispatchCaptureImageRequest()

        }

    }

    fun dispatchCaptureImageRequest() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {takePicIntent ->
            takePicIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePicIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }


}
