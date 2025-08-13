package com.jimleocortez.mayasendmoney.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jimleocortez.mayasendmoney.MainCoroutineRule
import com.jimleocortez.mayasendmoney.network.model.AccountDetails
import com.jimleocortez.mayasendmoney.network.model.LoginDetails
import com.jimleocortez.mayasendmoney.ui.home.HomeViewModel
import com.jimleocortez.mayasendmoney.ui.login.LoginViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `getAccountDetails`() = runTest {

        val viewModel = HomeViewModel()

        viewModel.getAccountDetails(12345)

        // Act
        coroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals("12345", viewModel.accountDetails.value.accountId)
        assertEquals("Jim Leo Cortez", viewModel.accountDetails.value.accountName)
        assertEquals(500.00, viewModel.accountDetails.value.accountBalance)
        assertEquals("101", viewModel.accountDetails.value.userId)
    }

}