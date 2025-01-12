package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import com.example.myapplication.ui.theme.MyApplicationTheme
// Colors
import androidx.compose.ui.graphics.Color
// Fonts
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

/* welcome Sign Languagers
    BEFORE YOU ADD ANYTHING TO THE CODE:
        1. go to the top left right next to you project where it says "main" or the branch you made
        2. Click on the right arrow next to main and click "Update"
        This will make sure your code is up to date before making changes
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
                    MyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Team Name", //Title
            fontSize = 50.sp, //Size of the text
            style = TextStyle(
                fontFamily = FontFamily( //setting the font of the text.
                    Font(R.font.lobster, FontWeight.Normal)
                    /* You can import a font by going to res/font and placing your font there.
                    It can only be lowercase letters */
                ),
                color = Color.Blue // color of text
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(50.dp)) // Add some space

        var textState = remember { TextFieldValue() }
        TextField(
            value = textState,
            onValueChange = { textState = it },
            label = { Text("Enter text") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(50.dp)) // Add some space

        Button(onClick = { /* Handle button click here */ }) {
            Text(text = "Play game")
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
