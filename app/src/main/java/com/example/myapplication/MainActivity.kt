package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text // Import for Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button // Import for Button

/* welcome Sign Languagers
    This is our NEDC code. Make sure to do the following after your done editing the code OTHERWISE YOUR EDITS WONT SAVE:

    1. On the top there is a tab "Git". Go to Git > New Branch. Enter your name so I know who made the change.
       a. If there is a red outline and it says branch already exists, select "Overwrite existing branch"
    2. Next, do the following
       a. If your on windows: Ctrl + Alt + A
       b. If your on Mac: Command + Option + A
    3. Go to Git > Commit. Select All your changes that you want to add. Make a commit message in the textbox below that describing what you did.
    4. Click on "Commit and Push".
 */

/* Vivaswan is my goat
*/


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = { /* Handle button click here */ }) {
        Text(text = "Play game")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}