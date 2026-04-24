package com.example.myapp.presentation.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.myapp.data.api.model.Product

@Composable
fun CategoryFilter(
    products: List<Product>,
    selectedCategory: String?,
    onSelect: (String?) -> Unit
) {
    val categories = products.map { it.category }.distinct()

    LazyRow(
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
    ) {
        items(categories) { category ->

            FilterChip(
                selected = selectedCategory == category,
                onClick = {
                    val newValue =
                        if (selectedCategory == category) null else category

                    onSelect(newValue)
                },
                label = { Text(category) },
                modifier = Modifier.padding(end = 8.dp),
                shape = CircleShape,
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFF2563EB),
                    selectedLabelColor = Color.White,
                    containerColor = Color.White,
                    labelColor = Color.Black
                )
            )
        }
    }
}