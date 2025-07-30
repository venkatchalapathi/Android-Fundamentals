package com.venkat.flow_demo

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyViewmodel: ViewModel() {

    fun countDown(): Flow<Int> =
        flow {
            for (i in 0..5) {
                delay(1000)
                Log.d("TAG", "countDown: $i")
                emit(i)
            }
        }
}