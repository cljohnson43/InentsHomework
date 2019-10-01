package com.example.inentshomework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Person(
    val name: String,
    val number: String,
    val email: String,
    val age: Int): Parcelable
