package com.example.inentshomework

import androidx.lifecycle.ViewModel

class RolodexViewModel : ViewModel() {
    var contacts: MutableList<Person> = mutableListOf()

    fun addPerson(person: Person): Person {
        if (person != null) {
            contacts.add(person)
        }
        return person
    }
}