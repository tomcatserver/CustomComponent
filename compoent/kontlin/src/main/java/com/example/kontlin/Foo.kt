package com.example.kontlin

import kotlin.Comparable

interface Foo {
    fun <T> singleList(item: T): List<T>
    fun <A> A.basicToS():String
    fun <T :Comparable<T>> sort(list: List<T>)

}