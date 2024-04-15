package com.alura.curso.composeapp.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alura.curso.composeapp.dao.ProductDao
import com.alura.curso.composeapp.ui.screens.ProductFormScreen
import com.alura.curso.composeapp.ui.theme.ComposeAppTheme

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAppTheme {
                Surface {
                    ProductFormScreen(onClickSave = { product ->
                        dao.save(product)
                        finish()
                    })
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
            ProductFormScreen(onClickSave = {})
        }
    }
}