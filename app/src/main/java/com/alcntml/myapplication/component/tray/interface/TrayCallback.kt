package com.alcntml.myapplication.component.tray.`interface`

import android.view.View

interface TrayCallback {
    fun onStateChange(view: View, state: Int, position: Int)
    fun onSlide(view: View, v: Float, position: Int)
}