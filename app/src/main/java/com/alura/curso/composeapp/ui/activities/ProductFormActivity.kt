package com.alura.curso.composeapp.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alura.curso.composeapp.R
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme
import java.math.BigDecimal

class ProductFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                Surface {
                    ProductFormScreen()
                }
            }
        }
    }
}

@Composable
fun ProductFormScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Criando o Produto",
            fontSize = 28.sp
        )

        var url by remember { mutableStateOf("") }

        if (url.isNotBlank()) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }

        TextField(
            value = url,
            onValueChange = { url = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da Imagem")
            }
        )

        var name by remember { mutableStateOf("") }
        TextField(
            value = name,
            onValueChange = { name = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            }
        )

        var price by remember { mutableStateOf("") }
        TextField(
            value = price,
            onValueChange = { price = it },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Preço")
            }
        )

        var description by remember { mutableStateOf("") }
        TextField(
            value = description,
            onValueChange = { description = it },
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text(text = "Descrição")
            }
        )

        Button(
            onClick = {
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
                Log.i("ProductFormScreen", "Product: $newProduct ")
            },
            Modifier.fillMaxWidth()
        ) {
            Text(text = "Salvar")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductFormScreenPreview() {
    ComposeAppTheme {
        Surface {
            ProductFormScreen()
        }
    }
}