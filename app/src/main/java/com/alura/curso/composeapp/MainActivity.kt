package com.alura.curso.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme
import com.alura.curso.composeapp.ui.theme.Purple500
import com.alura.curso.composeapp.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ProductItem()
                }
            }
        }
    }
}

@Composable
fun ProductItem() {
    Column(
        Modifier
            .width(200.dp)
            .height(250.dp)
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(brush = Brush.horizontalGradient(colors = listOf(Purple500, Teal200)))
        )
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            Modifier
                .size(100.dp)
                .offset(y = (-50).dp)
                .clip(shape = CircleShape)
                .align(Alignment.CenterHorizontally)

        )
        Text(text = "Texto 1")
        Text(text = "Texto 2")
    }
}


@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    ComposeAppTheme {
        Surface {
            ProductItem()
        }
    }
}
