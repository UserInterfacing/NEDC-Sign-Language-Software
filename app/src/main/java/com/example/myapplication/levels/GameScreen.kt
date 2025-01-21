package com.example.myapplication.levels

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GameScreen1(navController: NavController) {
    val bubbleLetters = listOf("A", "B", "C", "D", "E")
    var bubbles by remember { mutableStateOf(generateBubbles(bubbleLetters)) }

    // Track difficulty
    var difficulty by remember { mutableLongStateOf(5000L) } // Initial timer (5 seconds)
    var gameRunning by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        while (gameRunning) {
            val result = com.example.myapplication.GloveTranslator.translator()
            println("Translator result: $result")

            // Pop the corresponding bubble
            bubbles = bubbles.map { bubble ->
                if (bubble.letter == result) bubble.copy(isPopped = true) else bubble
            }

            // Remove popped bubbles and add new ones
            bubbles = bubbles.filterNot { it.isPopped } +
                    generateBubbles(bubbleLetters - bubbles.map { it.letter })

            delay(100)
        }
    }

    // Game layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        bubbles.forEach { bubble ->
            Bubble(
                bubble = bubble,
                onTimeout = { onGameOver(navController) },
                onPop = { letter ->
                    bubbles = bubbles.filterNot { it.letter == letter }
                    bubbles = bubbles + generateBubbles(
                        bubbleLetters - bubbles.map { it.letter }.toSet()
                    )
                    if (bubbles.isEmpty()) difficulty -= 500 // Increase difficulty
                },
                difficulty = difficulty
            )
        }
    }
}

// Data class for a bubble
data class Bubble(
    val letter: String,
    var size: Dp = 50.dp,
    var timer: Long = 5000L,
    val isPopped: Boolean = false,
    val x: Dp = Random.nextInt(0, 300).dp, // Random X position
    val y: Dp = Random.nextInt(0, 600).dp // Random Y position
)

// Generate bubbles for available letters
fun generateBubbles(letters: List<String>): List<Bubble> {
    return letters.map {
        Bubble(
            letter = it,
            size = Random.nextInt(50, 100).dp,
            timer = Random.nextLong(3000, 7000), // 3–7 seconds
            x = Random.nextInt(0, 300).dp, // Random X position
            y = Random.nextInt(0, 600).dp // Random Y position
        )
    }
}

@Composable
fun Bubble(
    bubble: Bubble,
    onTimeout: () -> Unit,
    onPop: (String) -> Unit,
    difficulty: Long
) {
    var currentSize by remember { mutableStateOf(bubble.size) }
    var timeLeft by remember { mutableStateOf(bubble.timer) }

    LaunchedEffect(Unit) {
        val tickRate = 50L
        while (timeLeft > 0) {
            delay(tickRate)
            timeLeft -= tickRate
            currentSize += (2.dp / difficulty.toFloat()) // Increase size relative to difficulty
        }
        if (timeLeft <= 0) onTimeout()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .absoluteOffset(x = bubble.x, y = bubble.y) // Position bubble
            .size(currentSize)
            .clip(CircleShape)
            .background(Color.Red)
            .padding(8.dp)
    ) {
        Text(text = bubble.letter, fontSize = 24.sp, color = Color.White)
        Text(text = "${timeLeft / 1000}s", fontSize = 12.sp, color = Color.Yellow)
    }
}

fun onGameOver(navController: NavController) {
    navController.navigate("game")
}
