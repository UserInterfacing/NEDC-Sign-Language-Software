package com.example.myapplication.levels

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun LevelOne(navController: NavController) {
    var currentImage by remember { mutableStateOf(1) } // Track which image to show
    var showFirstImage by remember { mutableStateOf(true) } // Control visibility of image1
    var showSecondImage by remember { mutableStateOf(false) } // Control visibility of image2

    val image1 = R.drawable.a // Replace with your actual image resource
    val image2 = R.drawable.b // Replace with your actual image resource

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Image 1 Animation
        AnimatedVisibility(
            visible = showFirstImage,
            exit = slideOutHorizontally(
                targetOffsetX = { -it }, // Slide out to the left
                animationSpec = tween(durationMillis = 500) // Exit animation duration
            ),
        ) {
            Image(
                painter = painterResource(id = image1),
                contentDescription = "First Image",
                modifier = Modifier.size(200.dp) // Adjust size as needed
            )
        }

        // Image 2 Animation
        AnimatedVisibility(
            visible = showSecondImage,
            enter = slideInHorizontally(
                initialOffsetX = { it }, // Slide in from the right
                animationSpec = tween(durationMillis = 500) // Enter animation duration
            ),
        ) {
            Image(
                painter = painterResource(id = image2),
                contentDescription = "Second Image",
                modifier = Modifier.size(200.dp) // Adjust size as needed
            )
        }
    }

    // Trigger the animations sequentially
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000) // Wait 2 seconds to display image1
        showFirstImage = false // Trigger image1 exit
        kotlinx.coroutines.delay(500) // Wait for image1 to exit completely
        showSecondImage = true // Trigger image2 entry
    }
}
