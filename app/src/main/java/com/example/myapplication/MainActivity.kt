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
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.levels.*

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
                    /*val isBluetoothEnabled = remember { mutableStateOf(BluetoothManager.isBluetoothEnabled(this)) }

                    if (isBluetoothEnabled.value) {
                        MyApp(modifier = Modifier.padding(innerPadding))
                    } else {
                        ConnectToBluetoothScreen {
                            isBluetoothEnabled.value = BluetoothManager.isBluetoothEnabled(this)
                        }
                    }*/

                    MyApp(modifier = Modifier.padding(innerPadding))
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
        composable("home") { HomeScreen(navController) }
        composable("stats") { StatsScreen(navController) }
        composable("settings") { SettingsScreen(navController) }
        composable("game") { GameActivity(navController) }

        //levels
        composable("level1") { LevelOne(navController) }
    }
}
