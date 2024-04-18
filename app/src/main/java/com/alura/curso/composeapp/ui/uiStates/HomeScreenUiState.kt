package com.alura.curso.composeapp.ui.uiStates

import com.alura.curso.composeapp.ui.model.Product

data class HomeScreenUiState(
    val sections: Map<String, List<Product>> = mutableMapOf(),
    val filterProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {},
) {
    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }
}