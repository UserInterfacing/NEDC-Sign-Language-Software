package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(navController: NavController) {
    Text(text = "This is the Settings Page")

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
                    painter = painterResource(id = R.drawable.home2),
                    contentDescription = "Book"
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
                    painter = painterResource(id = R.drawable.settings1),
                    contentDescription = "Settings"
                )
            }
        }
    }
}
