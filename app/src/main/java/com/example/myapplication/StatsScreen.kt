package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun StatsScreen(navController: NavController, statsViewModel: StatsViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Your Progress", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))

        // Level and XP Progress Bar
        LevelXPBar(level = statsViewModel.level, currentXP = statsViewModel.xp, xpToNextLevel = statsViewModel.xpToNextLevel())

        Spacer(modifier = Modifier.height(16.dp))

        // Display Stats
        StatsItem(label = "🔥 Day Streak", value = "${statsViewModel.dayStreak} days")
        StatsItem(label = "🏆 Score", value = "${statsViewModel.score} points")
        StatsItem(label = "📖 Words Learned", value = "${statsViewModel.wordsLearned} words")
        StatsItem(label = "⏳ Total Practice Time", value = "${statsViewModel.practiceTime} min")

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
                IconButton(onClick = { navController.navigate("home") }) {
                    Image(painter = painterResource(id = R.drawable.home2), contentDescription = "Home")
                }
                IconButton(onClick = { navController.navigate("stats") }) {
                    Image(painter = painterResource(id = R.drawable.stats1), contentDescription = "Stats")
                }
                IconButton(onClick = { navController.navigate("settings") }) {
                    Image(painter = painterResource(id = R.drawable.settings2), contentDescription = "Settings")
                }
            }
        }
    }
}

// XP Bar and Level Display
@Composable
fun LevelXPBar(level: Int, currentXP: Int, xpToNextLevel: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Level $level", fontSize = 20.sp, color = Color.Black)
        Spacer(modifier = Modifier.height(8.dp))

        // Progress Bar
        val progress = currentXP.toFloat() / xpToNextLevel.toFloat()
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(RoundedCornerShape(5.dp)),
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "$currentXP / $xpToNextLevel XP", fontSize = 14.sp, color = Color.Gray)
    }
}

// Reusable component for displaying a single statistic
@Composable
fun StatsItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontSize = 18.sp)
        Text(text = value, fontSize = 18.sp, color = Color.Blue)
    }
}

// ViewModel to manage stats data
class StatsViewModel : ViewModel() {
    var level by mutableStateOf(1) // Starting level
        private set
    var xp by mutableStateOf(30)  // Example current XP
        private set
    var dayStreak by mutableStateOf(7)  // Example value
        private set
    var score by mutableStateOf(1200)  // Example value
        private set
    var wordsLearned by mutableStateOf(50)  // Example value
        private set
    var practiceTime by mutableStateOf(300)  // Example value in minutes
        private set

    // Function to calculate XP needed for the next level
    fun xpToNextLevel(): Int {
        return 50 * level // Level 1 requires 50 XP, Level 2 requires 100 XP, etc.
    }
}

@Preview
@Composable
fun PreviewStatsScreen() {
    StatsScreen(navController = rememberNavController())
}
