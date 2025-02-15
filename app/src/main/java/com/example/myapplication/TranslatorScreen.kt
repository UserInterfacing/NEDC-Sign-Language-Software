package com.example.myapplication

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun TranslatorScreen(navController: NavController, translatorViewModel: TranslatorViewModel = viewModel()) {
    var translatedText by remember { mutableStateOf("") }

    // Runs the translation function repeatedly
    LaunchedEffect(Unit) {
        while (true) {
            val result = com.example.myapplication.GloveTranslator.translator()
            translatedText += "$result "
            delay(1000) // Simulated delay between translations
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // Title
        Text(
            text = "Translator",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Divider(color = Color.Gray, thickness = 1.dp)

        // Translation Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color.Cyan)
                .padding(16.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = translatedText, fontSize = 16.sp, color = Color.DarkGray)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Bottom Navigation Bar
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
                    onClick = { navController.navigate("home") },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.home2),
                        contentDescription = "Home"
                    )
                }
                IconButton(
                    onClick = { navController.navigate("stats") }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.stats2),
                        contentDescription = "Stats"
                    )
                }
                IconButton(
                    onClick = { navController.navigate("settings") }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings2),
                        contentDescription = "Settings"
                    )
                }
            }
        }
    }
}

// ViewModel (Handles State)
class TranslatorViewModel : ViewModel() {
    var translatedText by mutableStateOf("")
        private set
}

@Preview
@Composable
fun PreviewTranslatorScreen() {
    TranslatorScreen(navController = rememberNavController())
}
