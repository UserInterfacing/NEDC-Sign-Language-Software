package com.example.myapplication.levels

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
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
import kotlin.math.ceil
import kotlin.random.Random

@Composable
fun GameScreen1(navController: NavController) {
    var iter by remember { mutableStateOf(1) } // Tracks the iteration of the game
    var bubbles by remember { mutableStateOf(generateBubbles(ceil(iter / 2.0).toInt())) }
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
                Button(
                    onClick = { navController.navigate("home") }
                ) {
                    Text(text = "Home")
                }
            }
        }
        return
    }

    // Game logic
    LaunchedEffect(Unit) {
        while (gameRunning) {
            val result = com.example.myapplication.GloveTranslator.translator() // Get input
            println("Translator result: $result")
//
            // Pop the corresponding bubble if the result matches
            bubbles = bubbles.map { bubble ->
                if (bubble.letter == result) {
                    score += 10 // Increment the score for a successful pop
                    bubble.copy(isPopped = true)
                } else bubble
            }

            // Filter out popped bubbles
            bubbles = bubbles.filterNot { it.isPopped }

            // Check if all bubbles are popped
            if (bubbles.isEmpty()) {
                iter += 1 // Increment the iteration
                bubbles = generateBubbles(ceil(iter / 2.0).toInt()) // Generate new bubbles
            }

            delay(100) // Game loop delay
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
                onPop = {
                    score += 10
                    bubbles = bubbles.filterNot { it == bubble }
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

// Generate a specific number of bubbles
fun generateBubbles(count: Int): List<Bubble> {
    return List(count) {
        Bubble(
            letter = ('A'..'E').random().toString(), // Letters restricted from A to J
            size = 100.dp
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
        val tickRate = 50L // Milliseconds per shrink step
        while (currentSize > 0.dp) {
            delay(tickRate)
            currentSize -= 0.5.dp // Shrink the bubble slowly
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
