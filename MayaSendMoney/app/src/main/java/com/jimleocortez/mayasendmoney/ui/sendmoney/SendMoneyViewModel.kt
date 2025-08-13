package com.jimleocortez.mayasendmoney.ui.sendmoney

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimleocortez.mayasendmoney.network.model.AccountDetails
import com.jimleocortez.mayasendmoney.network.model.SendMoneyDetails
import com.jimleocortez.mayasendmoney.network.model.SendMoneyDetailsService
import com.jimleocortez.mayasendmoney.network.model.Transaction
import com.jimleocortez.mayasendmoney.network.model.TransactionHistoryResponse
import com.jimleocortez.mayasendmoney.network.model.TransactionHistoryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SendMoneyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is send Money Fragment"
    }
    val text: LiveData<String> = _text
    val accountDetails = MutableLiveData<AccountDetails>()
    val sendMoneyDetails = MutableLiveData<SendMoneyDetails>()

    fun validateSendMoneyAmount(amount: Double) : Boolean {
        return accountDetails.value.accountBalance.compareTo(amount) >= 0
    }

    suspend fun sendMoneyTransaction(sendMoneyDetails: SendMoneyDetails) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(SendMoneyDetailsService::class.java)
        try {
            val createdPost = apiService.createPost(sendMoneyDetails)
            this@SendMoneyViewModel.sendMoneyDetails.postValue(createdPost)

        } catch (e: Exception) {
            Log.e("TAG", e.message, e)
        }
    }
}