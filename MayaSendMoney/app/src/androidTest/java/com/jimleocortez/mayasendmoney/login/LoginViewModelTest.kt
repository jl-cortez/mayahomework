package com.jimleocortez.mayasendmoney.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jimleocortez.mayasendmoney.MainCoroutineRule
import com.jimleocortez.mayasendmoney.network.model.LoginDetails
import com.jimleocortez.mayasendmoney.ui.login.LoginViewModel
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `loginUserTest`() = runTest {

        val viewModel = LoginViewModel()

        val loginDetails = LoginDetails("username", "password")
        viewModel.validateLogin(loginDetails)

        // Act
        coroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals("username", viewModel.loginDetails.value.username)
        assertEquals("password", viewModel.loginDetails.value.password)
    }

    @Test
    fun `validateUser`() = runTest {

        val viewModel = LoginViewModel()

        val loginDetails = LoginDetails("username", "password")
        viewModel.validateLogin(loginDetails)

        // Act
        coroutineRule.testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertTrue(viewModel.isValidLogin(loginDetails.username, loginDetails.password))
    }

}