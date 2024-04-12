package com.alura.curso.composeapp.dao

import androidx.compose.runtime.mutableStateListOf
import com.alura.curso.composeapp.ui.model.Product

class ProductDao {

    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun products() = products.toList()

    fun save(product: Product) {
        products.add(product)
    }

}