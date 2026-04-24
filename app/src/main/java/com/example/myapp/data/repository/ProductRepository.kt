package com.example.myapp.data.api.repository

import com.example.myapp.data.api.ProductApi
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi
) {
    suspend fun getProducts() = api.getProducts()
    suspend fun getDetail(id: String) = api.getProductDetail(id)
}