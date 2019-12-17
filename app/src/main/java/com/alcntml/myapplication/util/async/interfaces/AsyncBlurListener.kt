package com.alcntml.myapplication.util.async.interfaces

import android.graphics.Bitmap

interface AsyncBlurListener{
    fun onLoad(bitmap: Bitmap?)
}