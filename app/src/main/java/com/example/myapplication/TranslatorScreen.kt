package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.unit.sp
//import com.google.accompanist.insets.ui.VerticalScrollbar
//import com.google.accompanist.insets.ui.ScrollbarAdapter

@Composable
fun TranslatorScreen(navController: NavController) {
    var onTranslatorScreen by remember { mutableStateOf(true) }
    var screenText by remember { mutableStateOf("") } // Use state for screenText
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        while (onTranslatorScreen) {
            val result = com.example.myapplication.GloveTranslator.translator()
            screenText += "$result " // Update state
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.Cyan)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Translation:",
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(16.dp)) // Adds space between texts
            Text(
                text = screenText,
                fontSize = 16.sp
            )
        }

//        VerticalScrollbar(
//            adapter = ScrollbarAdapter(scrollState),
//            modifier = Modifier.fillMaxHeight()
//        )
    }
    Spacer(modifier = Modifier.size(150.dp))
}