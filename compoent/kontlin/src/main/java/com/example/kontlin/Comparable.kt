package com.example.kontlin

interface Comparable<in T>{
    operator fun compareTo(other :T):Int
}