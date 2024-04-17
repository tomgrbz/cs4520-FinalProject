package com.example.cs4520_twitter

import com.example.cs4520_twitter.vms.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

class LoginViewModelTest {

    private val mockSignupApi =
    private val mockLoginAPI =
    private val underTest = LoginViewModel(mockLoginAPI, mockSignupApi )

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }
}