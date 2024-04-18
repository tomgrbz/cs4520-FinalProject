package com.example.cs4520_twitter

import com.example.cs4520_twitter.app_state.LoggedInUser
import com.example.cs4520_twitter.data_layer.api.LoginApi
import com.example.cs4520_twitter.data_layer.api.SignupApi
import com.example.cs4520_twitter.data_layer.api.models.CredentialsPostRequest
import com.example.cs4520_twitter.data_layer.api.models.CredentialsResponse
import com.example.cs4520_twitter.data_layer.database.UserEntity
import com.example.cs4520_twitter.vms.LoginViewModel


import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class LoginViewModelTest {

    private val mockSignupApi = MockSignupApi()
    private val mockLoginAPI = MockLoginApi()
    private val underTest = LoginViewModel(mockLoginAPI, mockSignupApi)

    @Before
    fun resetLogginUserID() {
        LoggedInUser.loggedInUserId = ""
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun whenLoginMatchesUsernameGiveBackUserObject() {
        assertEquals(false, underTest.isLoading.value)
        assertEquals("", LoggedInUser.loggedInUserId)


        underTest.login("test_user", "password123")

        assertEquals("1", LoggedInUser.loggedInUserId)
    }

    @Test
    fun whenGivenWrongCredsThrowsError() = runTest {
        assertEquals(false, underTest.isLoading.value)
        assertEquals("", LoggedInUser.loggedInUserId)
        underTest.login("12314524", "fakepw")
        assertEquals("", LoggedInUser.loggedInUserId)
    }

    @Test
    fun whenSignupPasses() = runTest {
        assertEquals(false, underTest.isLoading.value)
        assertEquals("", LoggedInUser.loggedInUserId)

        underTest.signUp("test_user", "password123")

        assertEquals("1", LoggedInUser.loggedInUserId)
    }
}

class MockLoginApi : LoginApi {
    override suspend fun login(requestBody: CredentialsPostRequest): CredentialsResponse {
        return if (requestBody.password == "password123" && requestBody.username == "test_user") {
            CredentialsResponse(UserEntity("1", "test_user", "password123", "res/bear"))
        } else {
            CredentialsResponse(UserEntity("", "", "", ""))
        }

    }
}

class MockSignupApi : SignupApi {
    override suspend fun signup(requestBody: CredentialsPostRequest): CredentialsResponse {
        return CredentialsResponse(UserEntity("1", "test_user", "password123", "res/bear"))
    }

}