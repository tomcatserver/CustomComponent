package com.example.kontlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    val one = 1
    val three = 30000
    val onelong = 1L
    val oneBy: Byte = 1

    val pi = 3.14
    val e = 2.71
    val flo = 2.75f
    val oneMillion = 1_000_0
    val cre = 1234_5678_9012_3456L
    val hex = 0xff_ec_de_5e
    val bytes = 0b111000_0
    val dk = 12
    val d: Int = 1
    val a: Int = 1
    val x = (1 shl 2) and 0x000ff000
    val s = "Hello,world!"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        printDouble(pi)
        control();
        val person = Person()

    }

    private fun control() {
        val b = 200
        val a = 100

        var max = 0;
        if (a < b) max = b
        println("max--=" + max)
        max = 0;
        if (a < b) {
            max = b
        } else {
            max = a
        }
        max = 0
        max = if (a < b) a else b

        max = if (a > b) {
            print("choose a")
            a
        } else {
            print("choose b")
            b

        }
        val x = 1;
        when (x) {
            1 -> print("x==1")
            2 -> print("x==2")
            else -> {
                print("x is neither 1 nor 2")
            }
        }
        when (x) {
            in 1 downTo 0 -> print("x==1")
            2 -> print("x==2")
            else -> {
                print("x is neither 1 nor 2")
            }
        }
        for (i in 1..3) {
            print(i)
        }
        for (i in 6 downTo 0 step 2) {
            print(i)
        }
         listOf<Int>(1, 2, 3, 45, 6).forEach { if (it == 3) return
            print(it)
        }
        listOf<Int>(12,3,4,5,5).forEach ss@{
            if(it==3)return@ss
            print(it)
        }
        listOf<Int>(1,2,3,4,5).forEach{
            if(it==3)return@forEach
            print(it)
        }
        listOf<Int>(1,2,3,4,5).forEach(fun (value:Int){
            if(value==3)return
            print(value)
        })
        listOf<Int>()
    }

    fun hasPrefix(x: Any) = when (x) {
        is String -> x.startsWith("prefix")
        else -> false
    }

    fun printDouble(b: Double) {
//        val arrayOf = arrayOf(1, 2, 3, 4)
//        val arrayOfNulls = arrayOfNulls<Int>(10)
//        val array = Array<Int>(5) { i -> (i * i).toInt() }

        val intArray = IntArray(5)
        intArray.forEach { println(b.toString() + "打印他他------" + it) }

        val intArray1 = IntArray(5) { 42 }
        intArray1.forEach { println(b.toString() + "打印他他------" + it) }
        val intArray2 = IntArray(5) { it * 1 }
        intArray2.forEach { println(b.toString() + "打印他他------" + it) }

        for (c in s) {
            print("字符串--- c=" + c)
        }
        val ss = "abc" + 1
        println(ss + "def")

        val text = """
    |Tell me and I forget.
    >Teach me and I remember.
    >Involve me and I learn.
    |(Benjamin Franklin)
    |;s;s;;skdkdkd
    >aalllll
            
        """.trimMargin(">")

        print("text==" + text)

        val i = 10
        println("iiooooo=$i")

        val s = "abc"
        println("$s.length is ${s.length}")

        val price = """
            ${'$'}9.99
        """.trimIndent()

        println("price=" + price)

    }

}
