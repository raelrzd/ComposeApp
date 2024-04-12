package com.alura.curso.composeapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alura.curso.composeapp.dao.ProductDao
import com.alura.curso.composeapp.sampledata.sampleCandies
import com.alura.curso.composeapp.sampledata.sampleDrinks
import com.alura.curso.composeapp.ui.screens.HomeScreen
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme

class MainActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(
                onClickFab = {
                    startActivity(Intent(this, ProductFormActivity::class.java))
                },
                content = {
                    val sections = mapOf(
                        "Todos produtos" to dao.products(),
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    )
                    HomeScreen(sections = sections)
//                    AllProductsScreen(sampleProducts)
                }
            )
        }
    }
}

@Composable
fun App(onClickFab: () -> Unit = {}, content: @Composable () -> Unit = {}) {
    ComposeAppTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = { onClickFab() }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }) { paddingValues ->
                Box(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AppPreview() {
    App()
}