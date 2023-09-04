package com.amlavati.todo.util

import android.util.Log
import com.amlavati.todo.BuildConfig


object LogUtil {
    const val LOG_TAG = "ToDoApp"
    fun printDebugLog(message: String) {
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, message)
        }
    }

    fun printDebugLog(tag: String, message: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, message)
    }
}