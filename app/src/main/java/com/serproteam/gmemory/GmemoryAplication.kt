package com.serproteam.gmemory

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GmemoryAplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        private var instance: GmemoryAplication? = null

        fun applicationContext() : GmemoryAplication {
            return instance!!
        }
    }
}