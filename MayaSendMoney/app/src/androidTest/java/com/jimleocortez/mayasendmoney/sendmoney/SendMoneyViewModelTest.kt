package com.jimleocortez.mayasendmoney.sendmoney

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jimleocortez.mayasendmoney.MainCoroutineRule
import com.jimleocortez.mayasendmoney.network.model.AccountDetails
import com.jimleocortez.mayasendmoney.network.model.LoginDetails
import com.jimleocortez.mayasendmoney.network.model.SendMoneyDetails
import com.jimleocortez.mayasendmoney.ui.home.HomeViewModel
import com.jimleocortez.mayasendmoney.ui.login.LoginViewModel
import com.jimleocortez.mayasendmoney.ui.sendmoney.SendMoneyViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class SendMoneyViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `sendMoneyTransaction`() = runTest {

        val viewModel = SendMoneyViewModel()
        val sendMoneyDetails = SendMoneyDetails(100.00, "UID123", "12345")

        viewModel.sendMoneyTransaction(sendMoneyDetails)

        // Act
        coroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(100.00, viewModel.sendMoneyDetails.value.amount)
        assertEquals("UID123", viewModel.sendMoneyDetails.value.transactionId)
        assertEquals("12345", viewModel.sendMoneyDetails.value.accountId)
    }

}