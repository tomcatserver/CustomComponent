package com.example.kontlin

interface Source<out T>{
    fun  nextT():T
}