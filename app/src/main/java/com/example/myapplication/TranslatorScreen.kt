package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TranslatorScreen(navController: NavController) {
    var onTranslatorScreen = true
    var screenText = ""
    LaunchedEffect(Unit) {
        while (onTranslatorScreen) {
            val result = com.example.myapplication.GloveTranslator.translator()
            screenText += "$result "
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Cyan)

    ) {
        Text("Translation:")
        Text(text = screenText)
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
            ) {
                Image(
                    painter = painterResource(id = R.drawable.book),
                    contentDescription = "Book"
                )
            }
            IconButton(
                onClick = { navController.navigate("stats") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.stats_icon),
                    contentDescription = "Stats"
                )
            }
            IconButton(
                onClick = { navController.navigate("settings") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.settings_icon),
                    contentDescription = "Settings"
                )
            }
        }
    }
}