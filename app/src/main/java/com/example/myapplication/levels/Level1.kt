package com.example.myapplication.levels

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import kotlinx.coroutines.delay

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
        Box (
          contentAlignment = Alignment.Center,
          modifier = Modifier
              .background(Color(0xFF00BF63))
              .fillMaxWidth()
              .height(150.dp)
        ) {
            Text(
                text = "LEVEL 1",
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
                        painter = painterResource(id = R.drawable.book),
                        contentDescription = "Book",
                    )
                }
                IconButton(
                    onClick = { navController.navigate("stats") },
                    modifier = Modifier.size(75.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.stats_icon),
                        contentDescription = "Stats"
                    )
                }
                IconButton(
                    onClick = { navController.navigate("settings") },
                    modifier = Modifier.size(75.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings_icon),
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
            navController.navigate("gameScreen1")
        }
    }
    onAnimationComplete()
}