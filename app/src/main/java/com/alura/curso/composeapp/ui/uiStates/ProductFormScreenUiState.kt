package com.alura.curso.composeapp.ui.uiStates

data class ProductFormScreenUiState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    var isPriceError: Boolean = false,
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
) {
    fun urlIsNotBlank(): Boolean {
        return url.isNotBlank()
    }
}