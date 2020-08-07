package com.example.kontlin

interface MyInterface {
    val prop :Int //抽象的
    val propertyWithImplementation : String get() = "foo"
    fun bar()
    fun foo(){
        //可选的方法体
        print(prop)
    }
}