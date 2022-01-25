package com.appsirise.core.ui

import androidx.fragment.app.Fragment

fun <T> Fragment.clearOnViewDestroy() = clearOnDestroy<T> { viewLifecycleOwner.lifecycle }
