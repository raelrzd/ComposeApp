package com.alura.curso.composeapp.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.alura.curso.composeapp.dao.ProductDao
import com.alura.curso.composeapp.sampledata.sampleCandies
import com.alura.curso.composeapp.sampledata.sampleDrinks
import com.alura.curso.composeapp.sampledata.sampleProducts
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.uiStates.HomeScreenUiState

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    var uiStateHolder: HomeScreenUiState by mutableStateOf(
        HomeScreenUiState(
            sections = mapOf(
                "Todos produtos" to dao.products(),
                "Promoções" to sampleDrinks + sampleCandies,
                "Doces" to sampleCandies,
                "Bebidas" to sampleDrinks
            ),
            onSearchChange = {
                uiStateHolder = uiStateHolder.copy(
                    searchText = it,
                    filterProducts = filterProducts(it)
                )
            }
        )
    )
        private set

    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(text, ignoreCase = true) || product.description?.contains(
            text, ignoreCase = true
        ) ?: false
    }

    private fun filterProducts(text: String): List<Product> = if (text.isNotBlank()) {
        sampleProducts.filter(containsInNameOrDescription(text)) + dao.products()
            .filter(containsInNameOrDescription(text))
    } else emptyList()

}