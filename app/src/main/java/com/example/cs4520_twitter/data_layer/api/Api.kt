package com.example.cs4520_twitter.data_layer.api


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Holds onto lazy instantiated retrofit client and api service object for given API endpoint
 */
object Api {
    const val BASE_URL: String = "http://tomgrbz.pythonanywhere.com/"
    const val BABS_ENDPOINT: String = "babs"
    const val USERS_ENDPOINT: String = "users"
    const val LOGIN_ENDPOINT: String = "login"
    const val SIGNUP_ENDPOINT: String = "signup"
    const val PROFILES_ENDPOINT: String = "profiles"
    const val FOLLOWS_ENDPOINT: String = "follows"

    private val babsRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val babsApiService: BabApi by lazy {
        babsRetrofit.create(BabApi::class.java)
    }

    private val usersRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val usersApiService: UsersApi by lazy {
        usersRetrofit.create(UsersApi::class.java)
    }


    private val loginRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val loginApiService: LoginApi by lazy {
        loginRetrofit.create(LoginApi::class.java)
    }


    private val signupRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val signupApiService: SignupApi by lazy {
        signupRetrofit.create(SignupApi::class.java)
    }

    private val profilesRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val profilesApiService: ProfilesApi by lazy {
        profilesRetrofit.create(ProfilesApi::class.java)
    }

    private val followsRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val followsApiService: FollowsApi by lazy {
        followsRetrofit.create(FollowsApi::class.java)
    }





}