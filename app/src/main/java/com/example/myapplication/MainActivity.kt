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
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

/* welcome Sign Languagers
    BEFORE YOU ADD ANYTHING TO THE CODE:

    1. go to the top left right next to you project where it says "main" or the branch you made
    2. Click on the right arrow next to main and click "Update" This will make sure your code is up to date before making changes
    3. Click on the right arrow next to main and click "Checkout" if you see it, otherwise ignore this step

    WHEN YOU DONE WITH THE CODE, do this OTHERWISE YOUR EDITS WONT SAVE:
    1. On the top there is a tab "Git" at the same place as "File" and "View". Go to Git > New Branch. Enter your name so I know who made the change.
        a. If there is a red outline and it says branch already exists, select "Overwrite existing branch"
    2. Next, do the following
        a. If your on windows: Ctrl + Alt + A
        b. If your on Mac: Command + Option + A
    3. Go to Git > Commit. Select All your changes that you want to add. Make a commit message in the textbox below that describing what you did.
    4. Click on "Commit and Push".
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val isBluetoothEnabled = remember { mutableStateOf(BluetoothManager.isBluetoothEnabled(this)) }

                    if (isBluetoothEnabled.value) {
                        MyApp(modifier = Modifier.padding(innerPadding))
                    } else {
                        ConnectToBluetoothScreen {
                            isBluetoothEnabled.value = BluetoothManager.isBluetoothEnabled(this)
                        }
                    }
                }
            }
        }
    }
}

object BluetoothManager {
    fun isBluetoothEnabled(context: Context): Boolean {
        val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as android.bluetooth.BluetoothManager
        val bluetoothAdapter = bluetoothManager.adapter
        return bluetoothAdapter?.isEnabled == true
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { MainActivity() }
        composable("stats") { StatsScreen() }
        composable("settings") { SettingsScreen() }
        //composable("game") { GameScreen() }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Team Name",
            fontSize = 50.sp,
            style = TextStyle(
                fontFamily = FontFamily(
                    Font(R.font.lobster, FontWeight.Normal)
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
                Text(
                    text = "TRANSLATOR"
                )
            }

            Button(
                onClick = {},
                modifier = Modifier.size(200.dp, 60.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "REVIEW"
                )
            }
        }

        Button(
            onClick = {},
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
                thickness = 2.dp, // Thickness of the top border
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
                ){
                    IconButton(
                        onClick = { /* Do something when book button is clicked */ },
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.book),
                            contentDescription = "Book" )
                    }
                    IconButton(
                        onClick = { navController.navigate("stats") }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.stats_icon),
                            contentDescription = "Stats" )
                    }
                    IconButton(
                        onClick = { /* Do something when settings button is clicked */ }
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

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    MyApplicationTheme {
        MyApp()
    }
}
