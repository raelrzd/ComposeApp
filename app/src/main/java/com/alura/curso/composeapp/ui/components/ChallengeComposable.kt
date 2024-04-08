package com.alura.curso.composeapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
private fun desafioLayout() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(100.dp)) {
        Box(
            Modifier
                .background(color = Color.Blue)
                .fillMaxWidth(0.2f)
                .fillMaxHeight())
        Column {
            Text(
                text = "Texto 1",
                Modifier
                    .fillMaxWidth()
                    .background(color = Color.Gray)
                    .padding(8.dp)
            )
            Text(text = "Texto 2", Modifier.padding(8.dp))
        }
    }
}