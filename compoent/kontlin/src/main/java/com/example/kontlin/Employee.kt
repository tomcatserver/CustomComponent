package com.example.kontlin

import android.icu.text.Transliterator

data class Employee(
        override val firstName: String,
        override val lastName: String,
        val position: Transliterator.Position
) : PersonMan
