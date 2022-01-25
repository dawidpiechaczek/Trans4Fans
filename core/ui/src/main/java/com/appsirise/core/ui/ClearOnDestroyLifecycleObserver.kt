package com.appsirise.core.ui

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.reflect.KProperty

class ClearOnDestroyLifecycleObserver<T>(private val lazyLifecycle: () -> Lifecycle) :
    LifecycleObserver {

    private var value: T? = null
    private val defaultLifecycleObserver = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            value = null
            super.onDestroy(owner)
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        value ?: throw IllegalStateException("Not initialized or outside of lifecycle.")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        lazyLifecycle().removeObserver(defaultLifecycleObserver)
        this.value = value
        lazyLifecycle().addObserver(defaultLifecycleObserver)
    }
}

fun <T> clearOnDestroy(lazyLifecycle: () -> Lifecycle) =
    ClearOnDestroyLifecycleObserver<T>(lazyLifecycle = lazyLifecycle)
