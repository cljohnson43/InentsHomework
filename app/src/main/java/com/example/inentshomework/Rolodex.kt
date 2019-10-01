package com.example.inentshomework

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_rolodex.*
import kotlinx.android.synthetic.main.activity_rolodex.view.*

const val REQUEST_PERSON_INFO = 43
const val ACTION_ADD_PERSON = "com.example.inentshomework.ACTION_ADD_PERSON"

class Rolodex : AppCompatActivity() {
    val model: RolodexViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(RolodexViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rolodex)

        model.contacts.forEach{ person ->
            addPersonView(person)
        }

    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.fabAddContact -> requestPerson()
        }
    }

    fun requestPerson() {
        val intent = Intent(this, AddPerson::class.java).apply {
            action = ACTION_ADD_PERSON
        }
        startActivityForResult(intent, REQUEST_PERSON_INFO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_PERSON_INFO && resultCode == Activity.RESULT_OK) {
            val person = data?.getParcelableExtra<Person>("person")
            if (person != null) {
                addPersonView(model.addPerson(person))
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun addPersonView(person: Person) {
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val personView: TextView = TextView(this)
        personView.layoutParams = params
        personView.setText("Name: ${person.name}\nNumber: ${person.number}\nEmail: ${person.email}\nAge: ${person.age.toString()}\n\n")
        rolodexLayout.addView(personView)
    }
}
