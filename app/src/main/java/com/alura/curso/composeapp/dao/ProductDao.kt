package com.alura.curso.composeapp.dao

import com.alura.curso.composeapp.sampledata.sampleProducts

class ProductDao {

    companion object {
        private val products = sampleProducts.toMutableList()
    }

    fun products() = products.toList()

}