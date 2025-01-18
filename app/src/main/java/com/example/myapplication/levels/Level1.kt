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
    var showFirstImage by remember { mutableStateOf(true) } // Control visibility of the first image
    var showSecondImage by remember { mutableStateOf(false) } // Control visibility of the second image
    var triggerAnimation by remember { mutableStateOf(false) } // Flag to trigger animation

    val images = listOf(R.drawable.a, R.drawable.b)
    val letters = listOf("Letter A", "Letter B", "Letter C", "Letter D", "Letter E")

    val currentLetter = letters[currentImage - 1] // Determine the current letter

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the letter text above the image
        Text(
            text = currentLetter,
            fontSize = 75.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp) // Space between text and image
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            // AnimatedVisibility for Image 1
            androidx.compose.animation.AnimatedVisibility(
                visible = showFirstImage,
                exit = slideOutHorizontally(
                    targetOffsetX = { -it }, // Slide out to the left
                    animationSpec = tween(durationMillis = 500) // Exit duration
                )
            ) {
                Image(
                    painter = painterResource(id = images[currentImage - 1]),
                    contentDescription = "Image $currentLetter",
                    modifier = Modifier.size(200.dp)
                )
            }

            // AnimatedVisibility for Image 2
            androidx.compose.animation.AnimatedVisibility(
                visible = showSecondImage,
                enter = slideInHorizontally(
                    initialOffsetX = { it }, // Slide in from the right
                    animationSpec = tween(durationMillis = 500) // Enter duration
                )
            ) {
                Image(
                    painter = painterResource(id = images[currentImage % images.size]),
                    contentDescription = "Image ${letters[currentImage % letters.size]}",
                    modifier = Modifier.size(200.dp)
                )
            }
        }
    }

    // Monitor the return value of Translator() and trigger animation if "A"
    LaunchedEffect(Unit) {
        while (true) {
            val result = com.example.myapplication.GloveTranslator.translator()
            println("Translator result: $result") // Debugging output
            if (result == "A") {
                triggerAnimation = true
                break // Exit Loop
            }
            kotlinx.coroutines.delay(100) // Check periodically
        }
    }

    // Run the animation when triggered
    LaunchedEffect(triggerAnimation) {
        println("Trigger animation: $triggerAnimation") // Debugging output
        if (triggerAnimation) {
            triggerAnimation = false // Reset trigger
            println("Animation starting...")

            // Exit the first image
            kotlinx.coroutines.delay(2000)
            showFirstImage = false
            println("First image hidden")

            // Update currentImage before showing the second image
            currentImage = (currentImage % images.size) + 1
            println("Current image updated to: $currentImage")

            // Enter the second image
            kotlinx.coroutines.delay(500)
            showSecondImage = true
            println("Second image visible")

            // Hold the second image
            kotlinx.coroutines.delay(2000)
            showSecondImage = false

            // Reset for the first image
            showFirstImage = true
            println("Animation complete")
        }
    }
}