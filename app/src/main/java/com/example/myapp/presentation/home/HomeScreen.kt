package com.example.myapp.presentation.home
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.myapp.Utiles.UiState
import com.example.myapp.data.api.model.Product
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.myapp.R
import com.example.myapp.presentation.home.components.HomeShimmer
import com.example.myapp.presentation.home.components.CategoryFilter
import com.example.myapp.presentation.home.components.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onClick: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val originalList by viewModel.original.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2563EB),
                    titleContentColor = Color.White
                )
            )
        },
        containerColor = Color(0xFFF3F3F5)
    ) { padding ->

        when (state) {

            is UiState.Loading -> {
                HomeShimmer()
            }

            is UiState.Success -> {

                val list = (state as UiState.Success<List<Product>>).data

                val listState = rememberSaveable(
                    saver = LazyListState.Saver
                ) { LazyListState() }

                var firstLoad by rememberSaveable { mutableStateOf(true) }

                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {

                    CategoryFilter(
                        products = originalList,
                        selectedCategory = selectedCategory,
                        onSelect = { viewModel.filter(it) }
                    )


                    LaunchedEffect(Unit) {
                        firstLoad = false
                    }

                    LazyColumn(state = listState) {
                        itemsIndexed(
                            items = list,
                            key = { _, item -> item.id }
                        ) { index, product ->

                            val offsetX by animateDpAsState(
                                targetValue = if (firstLoad) 80.dp else 0.dp,
                                animationSpec = tween(300)
                            )

                            Box(
                                modifier = Modifier.offset(x = offsetX)
                            ) {
                                ProductItem(product) {
                                    onClick(product.id.toString())
                                }
                            }
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
                        Button(onClick={viewModel.load()}) {
                            Text("Retry")
                        }
                    }
                }
            }
        }
    }
}