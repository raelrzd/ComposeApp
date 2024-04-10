package com.alura.curso.composeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.curso.composeapp.sampledata.sampleProducts
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme

@Composable
fun ProductsSection(title: String, products: List<Product>, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        LazyRow(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(products) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductsSectionPreview() {
    ComposeAppTheme {
        Surface {
            ProductsSection("Promoções", products = sampleProducts)
        }
    }
}