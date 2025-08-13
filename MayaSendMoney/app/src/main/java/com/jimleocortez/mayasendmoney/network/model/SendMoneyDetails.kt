package com.jimleocortez.mayasendmoney.network.model

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class SendMoneyDetails(
    val amount: Double,
    val transactionId: String,
    val accountId: String
)

interface SendMoneyDetailsService {
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): SendMoneyDetails

    @POST("posts")
    suspend fun createPost(@Body post: SendMoneyDetails): SendMoneyDetails
}