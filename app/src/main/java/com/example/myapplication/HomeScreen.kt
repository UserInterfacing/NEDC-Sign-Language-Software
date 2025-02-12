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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFCFAFA)) // Custom background color
            .padding(16.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        //Spacer(modifier = Modifier.height(.dp))
        Text(

            text = "NEDC APP",
            fontSize = 35.sp,
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.nexa, FontWeight.Normal)
                ),
                color = Color.Black
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Column(
            modifier = Modifier
                .padding(30.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(600.dp, 150.dp)
                    .clip(RectangleShape)
                    .clickable { navController.navigate("translator") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.translator),
                    contentDescription = "Translator"
                )
            }
            // Review Button
            Box(
                modifier = Modifier
                    .size(600.dp, 150.dp)
                    .clip(RectangleShape)
                    .clickable { /* Add your onClick action here */ }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.review),
                    contentDescription = "Review"
                )
            }

// Learn Button
            Box(
                modifier = Modifier
                    .size(600.dp, 150.dp)
                    .clip(RectangleShape)
                    .clickable { navController.navigate("game") }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.learn),
                    contentDescription = "Learn"
                )
            }

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
                        onClick = { navController.navigate("home ") },
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.home1),
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
                            painter = painterResource(id = R.drawable.settings2),
                            contentDescription = "Settings"
                        )
                    }
                }
            }
        }
    }
}
