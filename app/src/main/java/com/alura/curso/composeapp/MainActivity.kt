package com.alura.curso.composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alura.curso.composeapp.sampledata.sampleProducts
import com.alura.curso.composeapp.ui.screens.AllProductsScreen
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
        AllProductsScreen(sampleProducts)
//        HomeScreen(sampleSections)
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    App()
}