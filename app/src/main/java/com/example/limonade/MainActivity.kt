package com.example.limonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.limonade.ui.theme.LimonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimonadeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun StepUIDesign(modifier: Modifier = Modifier) {
    // Variables pour suivre l'état actuel et le nombre de pressions nécessaires
    var currentIndex by remember { mutableIntStateOf(1) }
    var squeezeTapCount by remember { mutableIntStateOf(1) }

    // Texte et image selon l'état
    val stepText = when (currentIndex) {
        1 -> R.string.instruction_tree
        2 -> R.string.instruction_lemon
        3 -> R.string.instruction_lemonade
        4 -> R.string.instruction_empty_glass
        else -> R.string.instruction_tree
    }
    val imageResource = when (currentIndex) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        4 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_tree
    }
    Text(
        text = stringResource(R.string.app_name), modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Yellow)
            .padding(8.dp), // Ajout de marges pour une meilleure visibilité
        color = Color.Black,
        textAlign = TextAlign.Center

    )

    Spacer(modifier = Modifier.height(100.dp))


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Texte pour l'application

        // Image avec comportement de clic
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = stepText),
            modifier = Modifier
                .size(200.dp)
                .background(color = Color(0xFF03DAC5))
                .clickable {
                    when (currentIndex) {
                        1 -> {
                            // Choisir un nombre aléatoire pour les pressions nécessaires
                            squeezeTapCount = (2..5).random()
                            currentIndex++
                        }
                        2 -> {
                            // Diminuer le compteur ou passer à l'étape suivante
                            if (squeezeTapCount > 1) {
                                squeezeTapCount--
                            } else {
                                currentIndex++
                            }
                        }
                        3 -> currentIndex++
                        4 -> currentIndex = 1
                    }
                }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Texte d'instruction
        Text(text = stringResource(id = stepText))
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LimonadeTheme {
        StepUIDesign(modifier = Modifier.fillMaxSize())
    }
}
