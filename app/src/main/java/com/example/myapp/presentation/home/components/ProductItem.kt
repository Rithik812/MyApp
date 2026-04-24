package com.example.myapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapp.data.api.model.Product
import com.example.myapp.R

@Composable
fun ProductItem(
    product: Product,
    onClick: () -> Unit
) {

    val imageUrl = product.images.firstOrNull() ?: product.thumbnail


    val stockText = when {
        product.stock > 50 -> "Available" to Color(0xFF4CAF50)
        product.stock in 1..50 -> "Limited" to Color(0xFFFF9800)
        else -> "Out of stock" to Color.Red
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                error = painterResource(R.drawable.image_not_available),
                placeholder = painterResource(R.drawable.image_not_available),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = 12.dp
                        )
                    )
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color =  Color(0xFF111827)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text("₹${product.price}",color = Color(0xFF374151))

                Text("${product.discountPercentage}% OFF")

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("⭐")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("${product.rating}")
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stockText.first,
                    color = stockText.second,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}