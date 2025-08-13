package com.jimleocortez.mayasendmoney.network.model

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class TransactionHistoryResponse(
    val transactions : ArrayList<Transaction>
)

interface TransactionHistoryService {
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): TransactionHistoryResponse

    @POST("posts")
    suspend fun createPost(@Body post: TransactionHistoryResponse): TransactionHistoryResponse
}