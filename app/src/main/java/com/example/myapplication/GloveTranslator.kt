package com.example.myapplication

object GloveTranslator {
    var message = ""

    fun translator2(receivedMessage: String) {
        message = receivedMessage
        println(message)
    }
    fun translator(): String {
        return message
    }
}