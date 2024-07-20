package com.norm.myremoteconfiggooglesheets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.theapache64.fig.Fig
import com.norm.myremoteconfiggooglesheets.ui.theme.MyRemoteConfigGoogleSheetsTheme
import kotlinx.coroutines.launch

enum class Season(val title: String) {
    Spring(title = "Spring"),
    Summer(title = "Summer"),
    Autumn(title = "Autumn"),
    Winter(title = "Winter");
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var season by remember {
                mutableStateOf(Season.Summer)
            }

            val fig = remember {
                Fig()
            }
            val scope = rememberCoroutineScope()

            MyRemoteConfigGoogleSheetsTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = season.title
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )
                    Button(
                        onClick = {
                            scope.launch {
                                fig.init("https://docs.google.com/spreadsheets/d/1ORZud-IrTd7hcKfvFJjqtkkZGl-tFsjzE0vxq6cL2Qg/edit?usp=sharing")
                                season = Season.valueOf(
                                    value = fig.getValue(key = "season", defaultValue = "Summer")
                                        ?: "Summer"
                                )
                            }
                        }
                    ) {
                        Text(
                            text = "Fetch"
                        )
                    }
                }
            }
        }
    }
}