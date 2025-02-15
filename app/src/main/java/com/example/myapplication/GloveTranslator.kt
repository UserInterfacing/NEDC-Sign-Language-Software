package com.example.myapplication

import kotlinx.coroutines.delay

object GloveTranslator {
    private var counter = 0 // Keeps track of the current state

    suspend fun translator(): String {
        //delay(10) // Wait for 500 milliseconds
        val result: String = when (counter % 10) {
            0 -> "A"
            1 -> "B"
            2 -> "C"
            3 -> "D"
            4 -> "E"
            5 -> "F"
            6 -> "G"
            7 -> "H"
            8 -> "I"
            9 -> "J"
            else -> "A" // Fallback, though not necessary
        }

        counter++ // Increment the counter for the next call
        return result
    }
}