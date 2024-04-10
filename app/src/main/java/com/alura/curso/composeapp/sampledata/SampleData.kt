package com.alura.curso.composeapp.sampledata

import com.alura.curso.composeapp.R
import com.alura.curso.composeapp.ui.model.Product
import java.math.BigDecimal

val sampleDataProduct = listOf(
    Product(
        name = "Hamburguer",
        price = BigDecimal("19.99"),
        image = R.drawable.burger
    ),
    Product(
        name = "Pizza",
        price = BigDecimal("29.99"),
        image = R.drawable.pizza
    ),
    Product(
        name = "Batata Frita",
        price = BigDecimal("14.99"),
        image = R.drawable.fries
    )
)