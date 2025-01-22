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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GameScreen1(navController: NavController) {
    val bubbleLetters = listOf("A", "B", "C", "D", "E")
    var bubbles by remember { mutableStateOf(generateBubbles(bubbleLetters)) }
    var gameRunning by remember { mutableStateOf(true) }
    var score by remember { mutableIntStateOf(0) }

    // Handle game over state
    if (!gameRunning) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Game Over",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Score: $score",
                    fontSize = 32.sp,
                    color = Color.White
                )
            }
        }
        return
    }

    // Game logic
    LaunchedEffect(Unit) {
        while (gameRunning) {
            val result = com.example.myapplication.GloveTranslator.translator()
            println("Translator result: $result")

            // Pop the corresponding bubble
            bubbles = bubbles.map { bubble ->
                if (bubble.letter == result) {
                    score += 10
                    bubble.copy(isPopped = true)
                } else bubble
            }

            // Remove popped bubbles
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
                onTimeout = { gameRunning = false },
                onPop = { letter ->
                    score += 10
                    bubbles = bubbles.filterNot { it.letter == letter }
                }
            )
        }
    }
}

// Data class for a bubble
data class Bubble(
    val letter: String,
    var size: Dp = 100.dp,
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
        )
    }
}

@Composable
fun Bubble(
    bubble: Bubble,
    onTimeout: () -> Unit,
    onPop: (String) -> Unit
) {
    var currentSize by remember { mutableStateOf(bubble.size) }

    LaunchedEffect(Unit) {
        val tickRate = 50L
        while (currentSize > 0.dp) {
            delay(tickRate)
            currentSize -= 1.dp // Shrink the bubble
        }
        if (currentSize <= 0.dp) {
            onTimeout() // Trigger game over
        }
    }

    if (currentSize > 0.dp) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .absoluteOffset(x = bubble.x, y = bubble.y)
                .size(currentSize)
                .clip(CircleShape)
                .background(Color.Red)
                .padding(8.dp)
        ) {
            Text(text = bubble.letter, fontSize = 24.sp, color = Color.White)
        }
    }
}
