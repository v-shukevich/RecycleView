package com.shukevich.recycleview.screens

import androidx.lifecycle.ViewModel
import com.shukevich.recycleview.tasks.Task

class Event<T>(
    private val value: T
) {

    private var handled: Boolean = false

    fun getValue(): T? {
        if (handled) return null
        handled = true
        return value
    }

}

open class BaseViewModel : ViewModel() {

    private val tasks = mutableListOf<Task<*>>()
// таска автомотіческі отменітся прі унічтоженіі вью моделі
    override fun onCleared() {
        super.onCleared()
        tasks.forEach { it.cancel() }
    }

    fun <T> Task<T>.autoCancel() {
        tasks.add(this)
    }
}