package com.appsirise.core.ui.extensions

import androidx.fragment.app.Fragment
import com.appsirise.core.ui.utils.clearOnDestroy

fun <T> Fragment.clearOnViewDestroy() = clearOnDestroy<T> { viewLifecycleOwner.lifecycle }
