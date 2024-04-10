package com.alura.curso.composeapp.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.curso.composeapp.R
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme
import java.math.BigDecimal

@Composable
fun ProductsSection() {
    Column {
        Text(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            text = "Promoções",
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductItem(
                Product(
                    name = "Hamburguer",
                    price = BigDecimal("19.99"),
                    image = R.drawable.burger
                )
            )
            ProductItem(
                Product(
                    name = "Pizza",
                    price = BigDecimal("29.99"),
                    image = R.drawable.pizza
                )
            )
            ProductItem(
                Product(
                    name = "Batata Frita",
                    price = BigDecimal("14.99"),
                    image = R.drawable.fries
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsSectionPreview() {
    ComposeAppTheme {
        ProductsSection()
    }
}