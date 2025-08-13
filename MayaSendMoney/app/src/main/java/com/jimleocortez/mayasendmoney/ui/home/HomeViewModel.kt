package com.jimleocortez.mayasendmoney.ui.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jimleocortez.mayasendmoney.R
import com.jimleocortez.mayasendmoney.network.model.AccountDetails
import com.jimleocortez.mayasendmoney.network.model.AccountDetailsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val accountDetails = MutableLiveData<AccountDetails>()


    suspend fun getAccountDetails(accountId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(AccountDetailsService::class.java)
        try {
            val newPost = AccountDetails(accountId.toString(), "Jim Leo Cortez", 500.00, "101")
            val createdPost = apiService.createPost(newPost)
            accountDetails.postValue(createdPost)

        } catch (e: Exception) {
            Log.e("TAG", e.message, e)
        }
    }

    fun maskString(originalString: String): String {
        return "*".repeat(originalString.length)
    }

    fun formatAmount(context: Context, amount: Double): String {
        return context.resources.getString(R.string.amount_format, String.format("%.2f", amount))
    }
}