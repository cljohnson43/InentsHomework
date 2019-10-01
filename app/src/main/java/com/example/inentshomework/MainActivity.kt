package com.example.inentshomework

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.provider.MediaStore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnDisplayImageActivity -> startDisplayImageActivity()
        }

    }

    fun startDisplayImageActivity() {
        val intent = Intent(this, DisplayImage::class.java)
        startActivity(intent)
    }
}
