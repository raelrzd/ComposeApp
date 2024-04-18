package com.alura.curso.composeapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alura.curso.composeapp.dao.ProductDao
import com.alura.curso.composeapp.sampledata.sampleCandies
import com.alura.curso.composeapp.sampledata.sampleDrinks
import com.alura.curso.composeapp.sampledata.sampleProducts
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.uiStates.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    private val _uiStateHolder: MutableStateFlow<HomeScreenUiState> =
        MutableStateFlow(HomeScreenUiState())
    val uiStateHolder get() = _uiStateHolder.asStateFlow()

    init {
        _uiStateHolder.update { currentState ->
            currentState.copy(
                onSearchChange = {
                    _uiStateHolder.value = _uiStateHolder.value.copy(
                        searchText = it,
                        filterProducts = filterProducts(it)
                    )
                }
            )
        }

        viewModelScope.launch {
            dao.products().collect { products ->
                _uiStateHolder.value = _uiStateHolder.value.copy(
                    sections = mapOf(
                        "Todos produtos" to products,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    filterProducts = filterProducts(_uiStateHolder.value.searchText)
                )
            }
        }
    }

    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(text, ignoreCase = true) || product.description?.contains(
            text, ignoreCase = true
        ) ?: false
    }

    private fun filterProducts(text: String): List<Product> = if (text.isNotBlank()) {
        sampleProducts.filter(containsInNameOrDescription(text)) + dao.products().value
            .filter(containsInNameOrDescription(text))
    } else emptyList()
}