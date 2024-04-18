package com.alura.curso.composeapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.alura.curso.composeapp.dao.ProductDao
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.uiStates.ProductFormScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

class ProductFormScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    private val _uiStateHolder: MutableStateFlow<ProductFormScreenUiState> =
        MutableStateFlow(ProductFormScreenUiState())
    val uiStateHolder get() = _uiStateHolder.asStateFlow()

    init {
        _uiStateHolder.update { currentState ->
            currentState.copy(
                onUrlChange = {
                    _uiStateHolder.value = _uiStateHolder.value.copy(
                        url = it
                    )
                },
                onNameChange = {
                    _uiStateHolder.value = _uiStateHolder.value.copy(
                        name = it
                    )
                },
                onPriceChange = {
                    _uiStateHolder.value = _uiStateHolder.value.copy(
                        isPriceError = try {
                            BigDecimal(it)
                            false
                        } catch (e: IllegalArgumentException) {
                            it.isNotEmpty()
                        },
                        price = it
                    )
                },
                onDescriptionChange = {
                    _uiStateHolder.value = _uiStateHolder.value.copy(
                        description = it
                    )
                }
            )
        }
    }

    fun save() {
        _uiStateHolder.value.run {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (e: NumberFormatException) {
                BigDecimal.ZERO
            }
            val newProduct = Product(
                name = name,
                price = convertedPrice,
                image = url,
                description = description
            )
            dao.save(newProduct)
        }
    }

}