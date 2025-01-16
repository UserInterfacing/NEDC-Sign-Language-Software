package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun GameActivity(navController: NavController) {
    Column(){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
            ) {
            Button(
                onClick = { LevelOne() }
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
}

fun LevelOne() {

}