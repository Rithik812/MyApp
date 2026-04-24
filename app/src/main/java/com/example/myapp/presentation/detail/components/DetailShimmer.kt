package com.example.myapp.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.presentation.common.shimmer.shimmerBrush
import androidx.compose.foundation.layout.size

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailShimmer() {

    val brush = shimmerBrush()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .height(18.dp)
                            .background(brush, RoundedCornerShape(4.dp))
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(12.dp)
                            .size(28.dp)
                            .background(brush, CircleShape)
                    )
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(brush)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                repeat(7) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(
                                when (index) {
                                    0 -> 0.9f   // title
                                    1 -> 0.7f   // desc
                                    else -> 0.5f
                                }
                            )
                            .height(if (index == 0) 20.dp else 14.dp)
                            .background(
                                brush,
                                RoundedCornerShape(6.dp)
                            )
                    )
                }
            }
        }
    }
}