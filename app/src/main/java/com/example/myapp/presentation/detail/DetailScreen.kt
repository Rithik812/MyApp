package com.example.myapp.presentation.home.detail
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.myapp.R
import com.example.myapp.Utiles.UiState
import com.example.myapp.data.api.model.Product
import com.example.myapp.presentation.detail.components.DetailShimmer

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    id: String,
    onClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load(id)
    }

    BackHandler {onClick()}

    when (state) {

        is UiState.Loading -> {
            DetailShimmer()
        }

        is UiState.Success<*> -> {

            val p = (state as UiState.Success<Product>).data

            val pagerState = rememberPagerState { p.images.size }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = p.title,
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                onClick()
                            }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = null)
                            }
                        },

                    )
                }
            ) { padding ->

                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        HorizontalPager(
                            state = pagerState,
                            modifier = Modifier.fillMaxSize()
                        ) { page ->
                            AsyncImage(
                                model = p.images[page],
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize(),
                                error = painterResource(R.drawable.image_not_available),
                                placeholder = painterResource(R.drawable.image_not_available),
                            )
                        }

                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(p.images.size) { index ->
                                Box(
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .size(8.dp)
                                        .background(
                                            if (pagerState.currentPage == index)
                                                Color.Black else Color.Gray,
                                            CircleShape
                                        )
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(p.title, fontWeight = FontWeight.Bold)
                        Text(p.description)
                        Text("Price: ₹${p.price}")
                        Text("Discount: ${p.discountPercentage}% OFF")
                        Text("Rating: ⭐ ${p.rating}")
                        Text("Stock: ${p.stock}")
                        Text("Brand: ${p.brand}")
                        Text("Category: ${p.category}")
                    }
                }
            }
        }

        is UiState.Error -> {
            val errorMessage=(state as UiState.Error).message
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(errorMessage)
                    Button(onClick={viewModel.load(id)}) {
                        Text("Retry")
                    }
                }
            }
        }
    }
}