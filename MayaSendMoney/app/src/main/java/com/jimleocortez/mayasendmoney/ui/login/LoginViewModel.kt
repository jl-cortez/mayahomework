package com.jimleocortez.mayasendmoney.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimleocortez.mayasendmoney.network.model.AccountDetails
import com.jimleocortez.mayasendmoney.network.model.AccountDetailsService
import com.jimleocortez.mayasendmoney.network.model.LoginDetails
import com.jimleocortez.mayasendmoney.network.model.LoginService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginViewModel : ViewModel() {
    fun isValidLogin(username: String, password: String): Boolean {
        return username == "username" && password == "password"
    }

    val loginDetails = MutableLiveData<LoginDetails>()


    suspend fun validateLogin(details: LoginDetails) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(LoginService::class.java)
        try {

            val createdPost = apiService.createPost(details)
            loginDetails.postValue(createdPost)

        } catch (e: Exception) {
            Log.e("TAG", e.message, e)
        }
    }
}