package com.jimleocortez.mayasendmoney.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


@Parcelize
data class AccountDetails(
    @SerializedName("accountId")
    val accountId: String,
    @SerializedName("accountName")
    val accountName: String,
    @SerializedName("accountBalance")
    val accountBalance: Double,
    @SerializedName("userId")
    val userId: String
) : Parcelable

interface AccountDetailsService {
    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): AccountDetails

    @POST("posts")
    suspend fun createPost(@Body post: AccountDetails): AccountDetails
}