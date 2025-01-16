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
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Atholton",
            fontSize = 50.sp,
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.nexa, FontWeight.Normal)
                ),
                color = Color.Blue
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {},
                modifier = Modifier.size(200.dp, 60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "TRANSLATOR")
            }

            Button(
                onClick = {},
                modifier = Modifier.size(200.dp, 60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "REVIEW")
            }
        }

        Button(
            onClick = {navController.navigate("game")},
            modifier = Modifier.size(400.dp, 100.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006400))
        ) {
            Text(
                text = "LEARN",
                fontSize = 30.sp,
                color = Color.Black
            )
        }

        Box {
            HorizontalDivider(
                color = Color.Black,
                thickness = 2.dp,
                modifier = Modifier.fillMaxWidth()
            )
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
}
