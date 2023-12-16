package com.example.veterinaria.SharedPreferens

import android.app.Application

class Shared : Application() {
    companion object{
        lateinit var prefs:SharedPrederenService
    }
    override fun onCreate() {
        super.onCreate()
        prefs=SharedPrederenService(applicationContext)
    }
}