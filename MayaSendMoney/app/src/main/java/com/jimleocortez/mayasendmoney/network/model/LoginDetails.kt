package com.jimleocortez.mayasendmoney.network.model

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class LoginDetails(
    var username: String,
    var password: String
)


interface LoginService {
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): LoginDetails

    @POST("posts")
    suspend fun createPost(@Body post: LoginDetails): LoginDetails
}