package com.jimleocortez.mayasendmoney.network.model

data class Transaction(
    val transactionType: String,
    val transactionId: String,
    val amount: Double
)