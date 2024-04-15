package com.alura.curso.composeapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alura.curso.composeapp.sampledata.sampleProducts
import com.alura.curso.composeapp.sampledata.sampleSections
import com.alura.curso.composeapp.ui.components.CardProductItem
import com.alura.curso.composeapp.ui.components.ProductsSection
import com.alura.curso.composeapp.ui.components.SearchTextField
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme

class HomeScreenStateHolder(
    val sections: Map<String, List<Product>> = mutableMapOf(),
    private val products: List<Product> = emptyList(),
    searchText: String = "",
) {

    var text by mutableStateOf(searchText)
        private set

    val filterProducts
        get() = if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) +
                    products.filter(containsInNameOrDescription())
        } else emptyList()

    private fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(text, ignoreCase = true)
                || product.description?.contains(text, ignoreCase = true) ?: false
    }

    fun isShowSections(): Boolean {
        return text.isBlank()
    }

    val onSearchChange: (String) -> Unit = { searchText ->
        text = searchText
    }

}

@Composable
fun HomeScreen(
    stateHolder: HomeScreenStateHolder = HomeScreenStateHolder(),
) {
    val sections = stateHolder.sections
    val filterProducts = remember(stateHolder.text) { stateHolder.filterProducts }
    Column {
        SearchTextField(
            searchText = stateHolder.text,
            onSearchChange = { stateHolder.onSearchChange(it) },
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (stateHolder.isShowSections()) {
                sections.forEach { section ->
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(filterProducts) { product ->
                    CardProductItem(product = product, Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    ComposeAppTheme {
        Surface {
            HomeScreen(HomeScreenStateHolder(sections = sampleSections))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenWithSearchTextPreview() {
    ComposeAppTheme {
        Surface {
            HomeScreen(
                stateHolder = HomeScreenStateHolder(
                    sections = sampleSections,
                    searchText = "pizza"
                )
            )
        }
    }
}