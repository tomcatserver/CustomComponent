package com.example.kontlin

class D:B,A {
    override fun foo() {
        super<B>.foo()
        super<A>.foo()

    }

    override fun bar() {
        super<B>.bar()
    }

}