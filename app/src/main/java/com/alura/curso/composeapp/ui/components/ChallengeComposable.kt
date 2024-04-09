package com.alura.curso.composeapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alura.curso.composeapp.R
import com.alura.curso.composeapp.ui.theme.Purple500
import com.alura.curso.composeapp.ui.theme.Teal200

@Preview(showBackground = true)
@Composable
private fun DesafioLayout() {
    Surface(Modifier.padding(8.dp), shape = RoundedCornerShape(15.dp), shadowElevation = 4.dp) {
        Row(
            Modifier
                .width(350.dp)
                .height(200.dp)
        ) {
            val imageSize = 100.dp
            Box(
                Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Purple500, Teal200
                            )
                        )
                    )
                    .width(imageSize)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    Modifier
                        .size(imageSize)
                        .offset(x = imageSize / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.Center)
                        .border(3.dp, Color.Magenta, CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(imageSize / 2))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .padding(15.dp),
                text = LoremIpsum(50).values.first(),
                fontSize = 16.sp,
                fontWeight = FontWeight(400),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}