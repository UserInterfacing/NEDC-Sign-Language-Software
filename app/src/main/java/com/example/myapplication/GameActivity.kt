package com.example.myapplication

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.navigation.NavController
import com.example.myapplication.levels.LevelOne

@Composable
fun GameActivity(navController: NavController) {
    Column(){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
            ) {
            Button(
                onClick = { navController.navigate("level1") }
            ) {
                Text(
                    text = "Level 1"
                )
            }

            Button(
                onClick = {}
            ) {
                Text(
                    text = "Level 2"
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {}
            ) {
                Text(
                    text = "Level 3"
                )
            }

            Button(
                onClick = {}
            ) {
                Text(
                    text = "Level 4"
                )
            }
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
                        painter = painterResource(id = R.drawable.home2),
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
}