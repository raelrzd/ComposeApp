package com.alura.curso.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alura.curso.composeapp.sampledata.sampleSections
import com.alura.curso.composeapp.ui.screens.HomeScreen
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
fun App() {
    ComposeAppTheme {
        HomeScreen(sampleSections)
//        AllProductsScreen(sampleProducts)
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    App()
}