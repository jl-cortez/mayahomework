package com.jimleocortez.mayasendmoney.transactionhistory

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jimleocortez.mayasendmoney.MainCoroutineRule
import com.jimleocortez.mayasendmoney.ui.transactions.TransactionHistoryViewModel
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class TransactionHistoryViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `getTransactionHistory`() = runTest {

        val viewModel = TransactionHistoryViewModel()

        viewModel.getTransactionHistory(12345)

        // Act
        coroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertNotNull(viewModel.transactionHistoryResponse.value)
        assertFalse(viewModel.transactionHistoryResponse.value.transactions.isEmpty())
    }

}