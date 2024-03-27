package com.romzc.secondapp.presentation.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.romzc.secondapp.domain.entities.SuperHero


@Composable
fun HeroDetailScreen(
    superHero: SuperHero
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 24.dp)
    ) {
        Text(text = superHero.heroName, fontWeight = FontWeight.ExtraBold, fontSize = 28.sp)
        Column(modifier = Modifier.padding(top = 20.dp)) {
            HistogramCompose(
                label = "Intelligence",
                width = superHero.powerStats.intelligence.toFloat(),
                height = 0f,
                color = Color.Black
            )
            HistogramCompose(
                label = "Strength",
                width = superHero.powerStats.strength.toFloat(),
                height = 0f,
                color = Color.Yellow
            )
            HistogramCompose(
                label = "Speed",
                width = superHero.powerStats.speed.toFloat(),
                height = 0f,
                color = Color.Red
            )
            HistogramCompose(
                label = "Durability",
                width = superHero.powerStats.durability.toFloat(),
                height = 0f,
                color = Color.Green
            )
            HistogramCompose(
                label = "Power",
                width = superHero.powerStats.power.toFloat(),
                height = 0f,
                color = Color.Cyan
            )
            HistogramCompose(
                label = "Combat",
                width = superHero.powerStats.combat.toFloat(),
                height = 0f,
                color = Color.Magenta
            )
        }
    }
}


@Composable
fun HistogramCompose(label: String, width: Float, height: Float, color: Color) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = if (label.length > 9) "${label.substring(0, 8)}..." else label,
            modifier = Modifier
                .widthIn(0.dp, 74.dp)
                .width(120.dp),
            overflow = TextOverflow.Ellipsis
        )
        Canvas(
            modifier = Modifier
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .fillMaxWidth()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawLine(
                start = Offset(x = 0f, y = canvasHeight / 2),
                end = Offset(x = (width * canvasWidth) / 100, y = canvasHeight / 2),
                color = color,
                strokeWidth = 54f
            )
        }
    }
}