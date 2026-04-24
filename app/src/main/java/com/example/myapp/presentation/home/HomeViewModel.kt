package com.example.myapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Utiles.UiState
import com.example.myapp.data.api.model.Product
import com.example.myapp.data.api.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Product>>> = _state

    private val _original = MutableStateFlow<List<Product>>(emptyList())
    val original: StateFlow<List<Product>> = _original

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory
    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {

                val res = repo.getProducts()

                if(res.statusCode==200){
                val list = res.data.data

                _original.value = list
                _state.value = UiState.Success(list)
                }
                else{
                    _state.value = UiState.Error("Error loading data")

                }

            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Error loading data")
            }
        }
    }

    fun filter(category: String?) {
        _selectedCategory.value = category

        val base = _original.value

        _state.value = UiState.Success(
            if (category == null) base
            else base.filter { it.category == category }
        )
    }
}