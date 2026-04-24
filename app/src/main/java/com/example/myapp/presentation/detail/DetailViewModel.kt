package com.example.myapp.presentation.home.detail

import android.R
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
class DetailViewModel @Inject constructor(
    private val repo: ProductRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UiState<Product>>(UiState.Loading)
    val state: StateFlow<UiState<Product>> = _state

    fun load(id: String) {
        viewModelScope.launch {
            try {
                val res = repo.getDetail(id)
                if(res.statusCode==200) {
                    _state.value = UiState.Success(res.data)
                }
                else{
                    _state.value = UiState.Error("Error loading data")
                }
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message.toString())
            }
        }
    }
}