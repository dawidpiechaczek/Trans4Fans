package com.appsirise.trans4fans.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.appsirise.trans4fans.R
import com.appsirise.trans4fans.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Trans4Fans)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
