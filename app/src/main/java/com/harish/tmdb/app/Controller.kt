package com.harish.tmdb.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Controller : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}
