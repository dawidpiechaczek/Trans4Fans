package com.appsirise.trans4fans.main

import com.appsirise.core.ui.base.BaseActivity
import com.appsirise.trans4fans.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setBinding() = ActivityMainBinding.inflate(layoutInflater)
}
