package com.example.myapp.data.api.model

data class ProductResponse(
    val statusCode: Int,
    val data: ProductWrapper
)

data class ProductWrapper(
    val data: List<Product>
)

data class ProductDetailResponse(
    val statusCode: Int,
    val data: Product
)

data class Product(
    val id: String,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)