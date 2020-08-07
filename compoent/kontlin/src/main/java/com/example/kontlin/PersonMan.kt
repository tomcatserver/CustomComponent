package com.example.kontlin

interface PersonMan : Named {
    val firstName: String
    val lastName: String
    override val name: String get() = "$firstName and $lastName"

}