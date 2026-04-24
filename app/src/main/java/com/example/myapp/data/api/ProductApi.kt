package com.example.myapp.data.api
import com.example.myapp.data.api.model.ProductDetailResponse
import com.example.myapp.data.api.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("api/v1/public/randomproducts")
    suspend fun getProducts(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 100
    ): ProductResponse

    @GET("api/v1/public/randomproducts/{id}")
    suspend fun getProductDetail(
        @Path("id") id: String
    ): ProductDetailResponse
}