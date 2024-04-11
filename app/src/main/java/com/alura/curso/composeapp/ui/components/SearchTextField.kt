package com.alura.curso.composeapp.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme

@Composable
fun SearchTextField(
    searchText: String,
    onSearchChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(value = searchText,
        onValueChange = { onSearchChange(it) },
        modifier = modifier,
        shape = RoundedCornerShape(100),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = null)
        },
        label = {
            Text(text = "Produto")
        },
        placeholder = {
            Text("O que você procura?")
        })
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldPreview() {
    ComposeAppTheme {
        Surface {
            SearchTextField(
                searchText = "",
                onSearchChange = {},
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchTextFieldWithSearchTextPreview() {
    ComposeAppTheme {
        Surface {
            SearchTextField(
                searchText = "a",
                onSearchChange = {},
            )
        }
    }
}