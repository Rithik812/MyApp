package com.example.myapp.presentation.home.components
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.presentation.common.shimmer.shimmerBrush

@Composable
fun HomeShimmer() {

    val brush = shimmerBrush()

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(brush)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .height(36.dp)
                        .width(80.dp)
                        .background(brush, RoundedCornerShape(20.dp))
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(6) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(
                            brush,
                            RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                brush,
                                RoundedCornerShape(8.dp)
                            )
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {

                        Box(
                            modifier = Modifier
                                .height(16.dp)
                                .width(150.dp)
                                .background(brush)
                        )

                        Box(
                            modifier = Modifier
                                .height(14.dp)
                                .width(100.dp)
                                .background(brush)
                        )

                        Box(
                            modifier = Modifier
                                .height(14.dp)
                                .width(60.dp)
                                .background(brush)
                        )
                    }
                }
            }
        }
    }
}