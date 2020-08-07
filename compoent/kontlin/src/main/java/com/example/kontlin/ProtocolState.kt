package com.example.kontlin

enum class ProtocolState {
    Wa {
        override fun signals() = tak
    },
    tak {
        override fun signals() = Wa
    };

    abstract fun signals(): ProtocolState

}