package com.venkat.view_model_demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel(){

    private val _count = MutableLiveData<Int>(0)
    val count : LiveData<Int> = _count

    fun increment(){
        _count.value = _count.value.plus(1)
    }
}