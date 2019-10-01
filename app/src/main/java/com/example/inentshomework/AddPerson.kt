package com.example.inentshomework

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.content_add_person.*

const val ACTION_RETURN_PERSON = "com.example.inentshomework.ACTION_RETURN_PERSON"

class AddPerson : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_add_person)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btnSubmit -> {
                val person = Person(
                    etName.text.toString(),
                    etNumber.text.toString(),
                    etEmail.text.toString(),
                    etAge.text.toString().toInt()
                )

                val receivedIntent = getIntent()
                if (receivedIntent.action == ACTION_ADD_PERSON) {
                    val intent = Intent(ACTION_RETURN_PERSON).apply {
                        putExtra("person", person)
                    }

                    setResult(Activity.RESULT_OK, intent)
                    finish()
                }
            }
        }

    }

}
