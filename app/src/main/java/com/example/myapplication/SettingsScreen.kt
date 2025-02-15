package com.example.myapplication

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SettingsScreen(navController: NavController, settingsViewModel: SettingsViewModel = viewModel()) {
    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = "Settings",
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )

        Divider(color = Color.Gray, thickness = 1.dp)

        // Dark Mode Toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Dark Mode")
            Switch(
                checked = settingsViewModel.isDarkModeEnabled,
                onCheckedChange = { settingsViewModel.toggleDarkMode() }
            )
        }

        // Notification Toggle
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Enable Notifications")
            Switch(
                checked = settingsViewModel.areNotificationsEnabled,
                onCheckedChange = { settingsViewModel.toggleNotifications() }
            )
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
                        painter = painterResource(id = R.drawable.settings1),
                        contentDescription = "Settings"
                    )
                }
            }
        }
    }
}

// ViewModel to handle settings state
class SettingsViewModel : androidx.lifecycle.ViewModel() {
    var isDarkModeEnabled by mutableStateOf(false)
        private set

    var areNotificationsEnabled by mutableStateOf(true)
        private set

    fun toggleDarkMode() {
        isDarkModeEnabled = !isDarkModeEnabled
    }

    fun toggleNotifications() {
        areNotificationsEnabled = !areNotificationsEnabled
    }
}

@Preview
@Composable
fun PreviewSettingsScreen() {
    SettingsScreen(navController = rememberNavController())
}
