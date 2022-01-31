package com.appsirise.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.appsirise.core.ui.extensions.clearOnViewDestroy

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    var binding: VB by clearOnViewDestroy()

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = setBinding().also(::binding::set).root

    protected abstract fun setBinding(): VB
}
