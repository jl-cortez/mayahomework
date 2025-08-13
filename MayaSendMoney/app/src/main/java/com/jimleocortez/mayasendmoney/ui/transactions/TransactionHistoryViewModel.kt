package com.jimleocortez.mayasendmoney.ui.transactions

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jimleocortez.mayasendmoney.network.model.Transaction
import com.jimleocortez.mayasendmoney.network.model.TransactionHistoryResponse
import com.jimleocortez.mayasendmoney.network.model.TransactionHistoryService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TransactionHistoryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    val transactionHistoryResponse = MutableLiveData<TransactionHistoryResponse>()

    suspend fun getTransactionHistory(accountId: Int) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(TransactionHistoryService::class.java)
        try {

            val transactions = arrayListOf(
                Transaction("debit","TXN001", 1500.0),
                Transaction("debit","TXN002", 2300.5),
                Transaction("debit","TXN003", 875.75),
                Transaction("credit","TXN004", 112.3)
            )

            val newPost = TransactionHistoryResponse(transactions)

            val createdPost = apiService.createPost(newPost)
            transactionHistoryResponse.postValue(createdPost)

        } catch (e: Exception) {
            Log.e("TAG", e.message, e)
        }
    }
}