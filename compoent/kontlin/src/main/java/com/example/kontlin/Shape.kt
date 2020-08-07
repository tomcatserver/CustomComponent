package com.example.kontlin

open class Shape {
    fun getName() {
        print("class Shape")
    }
    fun toStrings():String{
        return toString()
    }
    val <T> List<T>.lastIndexs:Int get() = size-1
}