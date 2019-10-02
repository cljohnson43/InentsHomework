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
            R.id.btnGoToRolodex -> startRolodexActivity()
            R.id.btnGoToEMICalculator -> startEMICalculatorActivity()
        }

    }

    fun startDisplayImageActivity() {
        val intent = Intent(this, DisplayImage::class.java)
        startActivity(intent)
    }

    fun startRolodexActivity() {
        val intent = Intent(this, Rolodex::class.java)
        startActivity(intent)
    }

    fun startEMICalculatorActivity() {
        val intent = Intent(this, EMICalculator::class.java)
        startActivity(intent)
    }
}
