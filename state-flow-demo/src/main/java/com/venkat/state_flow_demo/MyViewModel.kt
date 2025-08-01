package com.venkat.state_flow_demo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val _count = MutableStateFlow<Int>(0)

    val count = _count.asStateFlow()

    fun incrementCount() {
        _count.value +=1
    }

    private val _event = MutableSharedFlow<String>(2)
    val event = _event.asSharedFlow()

    fun showEvent(){
        viewModelScope.launch {
            _event.emit("Event Triggered")
            _event.emit("Event Triggered 2")
            _event.emit("Event Triggered 3")
        }
    }

}