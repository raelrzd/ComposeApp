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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alura.curso.composeapp.dao.ProductDao
import com.alura.curso.composeapp.sampledata.sampleCandies
import com.alura.curso.composeapp.sampledata.sampleDrinks
import com.alura.curso.composeapp.sampledata.sampleProducts
import com.alura.curso.composeapp.sampledata.sampleSections
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.screens.HomeScreen
import com.alura.curso.composeapp.ui.screens.HomeScreenStateHolder
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
                    val products = dao.products()
                    val sections = mapOf(
                        "Todos produtos" to products,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    )

                    var text by remember { mutableStateOf("") }

                    fun containsInNameOrDescription() = { product: Product ->
                        product.name.contains(text, ignoreCase = true)
                                || product.description?.contains(text, ignoreCase = true) ?: false
                    }

                    val filterProducts = remember(products, text) {
                        if (text.isNotBlank()) {
                            sampleProducts.filter(containsInNameOrDescription()) +
                                    products.filter(containsInNameOrDescription())
                        } else emptyList()
                    }

                    val homeScreenStateHolder = remember(products, text) {
                        HomeScreenStateHolder(
                            sections = sections,
                            filterProducts = filterProducts,
                            searchText = text,
                            onSearchChange = { text = it }
                        )
                    }

                    HomeScreen(stateHolder = homeScreenStateHolder)
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
    App {
        HomeScreen(HomeScreenStateHolder(sections = sampleSections))
    }
}