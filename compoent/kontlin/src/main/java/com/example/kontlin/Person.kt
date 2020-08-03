package com.example.kontlin

open class Person {
    open val count:Int=0
    constructor(pa: String) {
        print("我后执行")

    }

    constructor (name: String, age: Int) {

    }

    constructor() {
        print("我后执行")
    }

    init {
        print("我先执行")
    }
     open fun draw(){

    }


}