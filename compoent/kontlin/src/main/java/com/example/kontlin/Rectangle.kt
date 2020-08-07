package com.example.kontlin

class Rectangle :Shape()
fun Shape.getName(int: Int) = "Shape"
fun Rectangle.getName() = "Rectangle"
fun printClassName(s: Shape) {
    println(s.getName(1))
}
