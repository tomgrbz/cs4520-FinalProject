package com.example.cs4520_twitter.application


import android.app.Application

/**
 * Custom application instance that holds onto a app container object for dependency injection
 * and instantiation of local and remote sources
 */
class BabbleApplication : Application() {
    val appContainer: AppContainer = AppContainer()

}