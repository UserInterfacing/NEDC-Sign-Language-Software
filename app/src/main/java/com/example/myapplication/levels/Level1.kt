package com.example.myapplication.levels

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun LevelOne(navController: NavController) {
    var currentImage by remember { mutableIntStateOf(1) } // Track the current image index
    var showFirstImage by remember { mutableStateOf(true) }
    var showSecondImage by remember { mutableStateOf(false) }
    var showThirdImage by remember { mutableStateOf(false) }
    var showFourthImage by remember { mutableStateOf(false) }
    var showFifthImage by remember { mutableStateOf(false) }
    var expectedLetter by remember { mutableStateOf("A") } // Track the expected letter

    val images = listOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.d,
        R.drawable.e
    )
    val letters = listOf("Letter A", "Letter B", "Letter C", "Letter D", "Letter E")

    val currentLetter = letters[currentImage - 1]

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentLetter,
            fontSize = 75.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // Add visibility for all images
            androidx.compose.animation.AnimatedVisibility(
                visible = showFirstImage,
                exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
            ) {
                Image(
                    painter = painterResource(id = images[0]),
                    contentDescription = "Image A",
                    modifier = Modifier.size(200.dp)
                )
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = showSecondImage,
                enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)),
                exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
            ) {
                Image(
                    painter = painterResource(id = images[1]),
                    contentDescription = "Image B",
                    modifier = Modifier.size(200.dp)
                )
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = showThirdImage,
                enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)),
                exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
            ) {
                Image(
                    painter = painterResource(id = images[2]),
                    contentDescription = "Image C",
                    modifier = Modifier.size(200.dp)
                )
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = showFourthImage,
                enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500)),
                exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
            ) {
                Image(
                    painter = painterResource(id = images[3]),
                    contentDescription = "Image D",
                    modifier = Modifier.size(200.dp)
                )
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = showFifthImage,
                enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500))
            ) {
                Image(
                    painter = painterResource(id = images[4]),
                    contentDescription = "Image E",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }

    // Monitor translator and trigger animation
    LaunchedEffect(Unit) {
        while (true) {
            val result = com.example.myapplication.GloveTranslator.translator()
            println("Translator result: $result")

            if (result == expectedLetter) {
                triggerAnimationForLetter(
                    expectedLetter,
                    onAnimationComplete = {
                        // Update currentImage and expectedLetter only if we haven't reached "E"
                        if (currentImage < images.size) {
                            currentImage++
                            expectedLetter = when (expectedLetter) {
                                "A" -> "B"
                                "B" -> "C"
                                "C" -> "D"
                                "D" -> "E"
                                else -> "E" // Stop at "E"
                            }
                        }
                    },
                    showFirstImage = { showFirstImage = it },
                    showSecondImage = { showSecondImage = it },
                    showThirdImage = { showThirdImage = it },
                    showFourthImage = { showFourthImage = it },
                    showFifthImage = { showFifthImage = it }
                )
            }
            kotlinx.coroutines.delay(100)
        }
    }
}

// Function to trigger animations
private fun triggerAnimationForLetter(
    letter: String,
    onAnimationComplete: () -> Unit,
    showFirstImage: (Boolean) -> Unit,
    showSecondImage: (Boolean) -> Unit,
    showThirdImage: (Boolean) -> Unit,
    showFourthImage: (Boolean) -> Unit,
    showFifthImage: (Boolean) -> Unit
) {
    println("Triggering animation for letter: $letter")
    when (letter) {
        "A" -> {
            showFirstImage(false)
            showSecondImage(true)
        }
        "B" -> {
            showSecondImage(false)
            showThirdImage(true)
        }
        "C" -> {
            showThirdImage(false)
            showFourthImage(true)
        }
        "D" -> {
            showFourthImage(false)
            showFifthImage(true)
        }
        "E" -> {
            println("success")
        }
    }
    onAnimationComplete()
}