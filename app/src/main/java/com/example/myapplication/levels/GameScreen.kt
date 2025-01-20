import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun GameScreen(navController: NavController, gameId: Int) {
    var boxes by remember { mutableStateOf(listOf("A", "B", "C", "D", "E")) }
    var targetBox by remember { mutableStateOf("") }
    var playerPosition by remember { mutableStateOf("") }
    var gameMessage by remember { mutableStateOf("Get Ready!") }

    LaunchedEffect(Unit) {
        while (true) {
            // Randomly pick a target box
            targetBox = boxes.random()
            boxes = boxes.filter { it != targetBox } // Remove target box
            gameMessage = "Align your icon with box $targetBox!"

            // Wait for player input (simulate translator)
            val result = com.example.myapplication.GloveTranslator.translator()
            playerPosition = result

            // Check collision
            if (playerPosition == targetBox) {
                gameMessage = "Success! Box aligned."
            } else {
                gameMessage = "Missed! Game Over."
            }

            // Respawn boxes
            boxes = listOf("A", "B", "C", "D", "E")
            delay(2000) // Wait before starting a new round
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = gameMessage,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            boxes.forEach { boxLabel ->
                Box(
                    modifier = Modifier
                        .size(100.dp, 50.dp)
                        .background(if (boxLabel == targetBox) Color.Red else Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = boxLabel, color = Color.White, fontSize = 24.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .size(100.dp, 50.dp)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Player: $playerPosition",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}
