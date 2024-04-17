package com.alura.curso.composeapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.alura.curso.composeapp.R
import com.alura.curso.composeapp.ui.model.Product
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme
import java.math.BigDecimal

class ProductFormScreenStateHolder(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description: String = "",
    var isPriceError: Boolean = false,
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onClickSave: () -> Unit = {},
) {
    fun urlIsNotBlank(): Boolean {
        return url.isNotBlank()
    }
}

@Composable
fun ProductFormScreen(onClickSave: (Product) -> Unit = {}) {
    var url by rememberSaveable { mutableStateOf("") }

    var name by rememberSaveable { mutableStateOf("") }

    var price by rememberSaveable { mutableStateOf("") }
    var isPriceError by rememberSaveable { mutableStateOf(false) }

    var description by rememberSaveable { mutableStateOf("") }

    val productFormScreenStateHolder = ProductFormScreenStateHolder(
        url = url,
        name = name,
        price = price,
        description = description,
        isPriceError = isPriceError,
        onUrlChange = { url = it },
        onNameChange = { name = it },
        onPriceChange = {
            isPriceError = try {
                BigDecimal(it)
                false
            } catch (e: IllegalArgumentException) {
                it.isNotEmpty()
            }
            price = it
        },
        onDescriptionChange = { description = it },
        onClickSave = {
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
            onClickSave(newProduct)
        }
    )
    ProductFormScreen(stateHolder = productFormScreenStateHolder)
}

@Composable
fun ProductFormScreen(
    stateHolder: ProductFormScreenStateHolder = ProductFormScreenStateHolder(),
) {
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

        if (stateHolder.urlIsNotBlank()) {
            AsyncImage(
                model = stateHolder.url,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder)
            )
        }

        TextField(
            value = stateHolder.url,
            onValueChange = { stateHolder.onUrlChange(it) },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Url da Imagem")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = stateHolder.name,
            onValueChange = { stateHolder.onNameChange(it) },
            Modifier.fillMaxWidth(),
            label = {
                Text(text = "Nome")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Words
            )
        )

        Column {
            TextField(
                value = stateHolder.price,
                onValueChange = { stateHolder.onPriceChange(it) },
                Modifier.fillMaxWidth(),
                isError = stateHolder.isPriceError,
                label = {
                    Text(text = "Preço")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
            )
            if (stateHolder.isPriceError) {
                Text(
                    text = "Preço deve ser um número decimal",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }

        TextField(
            value = stateHolder.description,
            onValueChange = { stateHolder.onDescriptionChange(it) },
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = {
                Text(text = "Descrição")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Button(
            onClick = { stateHolder.onClickSave() },
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
            ProductFormScreen(onClickSave = {})
        }
    }
}