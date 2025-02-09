package com.example.myapplication.levels

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun LevelTwo(navController: NavController) {
    var currentImage by remember { mutableIntStateOf(1) } // Track the current image index
    var showFirstImage by remember { mutableStateOf(true) }
    var showSecondImage by remember { mutableStateOf(false) }
    var showThirdImage by remember { mutableStateOf(false) }
    var showFourthImage by remember { mutableStateOf(false) }
    var showFifthImage by remember { mutableStateOf(false) }
    var expectedLetter by remember { mutableStateOf("F") } // Track the expected letter

    val images = listOf(
        R.drawable.f,
        R.drawable.g,
        R.drawable.h,
        R.drawable.i,
        R.drawable.j
    )
    val letters = listOf("Letter F", "Letter G", "Letter H", "Letter I", "Letter J")

    val currentLetter = letters[currentImage - 1]

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color(0xFF00BF63))
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Text(
                text = "LEVEL 2",
                fontSize = 75.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
            )
        }

        Spacer(modifier = Modifier.size(25.dp))

        Text(
            text = currentLetter,
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
        )

        Spacer(modifier = Modifier.size(75.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopCenter
        ) {
            // Add visibility for all images
            androidx.compose.animation.AnimatedVisibility(
                visible = showFirstImage,
                exit = slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(500))
            ) {
                Image(
                    painter = painterResource(id = images[0]),
                    contentDescription = "Image F",
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
                    contentDescription = "Image G",
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
                    contentDescription = "Image H",
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
                    contentDescription = "Image I",
                    modifier = Modifier.size(200.dp)
                )
            }
            androidx.compose.animation.AnimatedVisibility(
                visible = showFifthImage,
                enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(500))
            ) {
                Image(
                    painter = painterResource(id = images[4]),
                    contentDescription = "Image J",
                    modifier = Modifier.size(200.dp)
                )
            }
        }

        Spacer(modifier = Modifier.size(50.dp))

        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .height(90.dp)
        ) {
            Text(
                text = "Make this with your glove",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
            )
        }

        BottomAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("home")
                    },
                    modifier = Modifier.size(75.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home1),
                        contentDescription = "Book",
                    )
                }
                IconButton(
                    onClick = { navController.navigate("stats") },
                    modifier = Modifier.size(75.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.stats2),
                        contentDescription = "Stats"
                    )
                }
                IconButton(
                    onClick = { navController.navigate("settings") },
                    modifier = Modifier.size(75.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings2),
                        contentDescription = "Settings"
                    )
                }
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
                    navController = navController,
                    onAnimationComplete = {
                        // Update currentImage and expectedLetter only if we haven't reached "J"
                        if (currentImage < images.size) {
                            currentImage++
                            expectedLetter = when (expectedLetter) {
                                "F" -> "G"
                                "G" -> "H"
                                "H" -> "I"
                                "I" -> "J"
                                else -> "J" // Stop at "J"
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
    navController: NavController,
    onAnimationComplete: () -> Unit,
    showFirstImage: (Boolean) -> Unit,
    showSecondImage: (Boolean) -> Unit,
    showThirdImage: (Boolean) -> Unit,
    showFourthImage: (Boolean) -> Unit,
    showFifthImage: (Boolean) -> Unit
) {
    println("Triggering animation for letter: $letter")
    when (letter) {
        "F" -> {
            showFirstImage(false)
            showSecondImage(true)
        }
        "G" -> {
            showSecondImage(false)
            showThirdImage(true)
        }
        "H" -> {
            showThirdImage(false)
            showFourthImage(true)
        }
        "I" -> {
            showFourthImage(false)
            showFifthImage(true)
        }
        "J" -> {
            navController.navigate("gameScreen1")
        }
    }
    onAnimationComplete()
}
