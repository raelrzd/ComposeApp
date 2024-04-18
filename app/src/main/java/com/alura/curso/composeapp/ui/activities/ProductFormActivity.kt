package com.alura.curso.composeapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alura.curso.composeapp.ui.screens.ProductFormScreen
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme
import com.alura.curso.composeapp.ui.viewmodels.ProductFormScreenViewModel

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                Surface {
                    val viewModel: ProductFormScreenViewModel by viewModels()
                    ProductFormScreen(
                        viewModel = viewModel,
                        onClickSave = { finish() }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductFormActivityPreview() {
    ComposeAppTheme {
        Surface {
            ProductFormScreen(viewModel = ProductFormScreenViewModel(), onClickSave = {})
        }
    }
}