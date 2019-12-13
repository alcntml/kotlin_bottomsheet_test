package com.alcntml.myapplication.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.view.View
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.ScriptIntrinsicBlur
import android.renderscript.RenderScript
import androidx.annotation.RequiresApi
import kotlin.math.roundToInt

public class BlurUtil {

    private val BITMAP_SCALE = 0.4f
    //Set the radius of the Blur. Supported range 0 < radius <= 25
    private var BLUR_RADIUS = 3.5f

    fun getBitmapFromView(view: View): Bitmap? {
        val height = view.height
        val width = view.width
        if (height != 0 && width != 0) {
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }
        return null
    }

    fun getBitmapFromView(view: View, defaultColor: Int): Bitmap? {
        val height = view.height
        val width = view.width
        if (height != 0 && width != 0) {
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            canvas.drawColor(defaultColor)
            view.draw(canvas)
            return bitmap
        }
        return null
    }

    /*@RequiresApi(Build.VERSION_CODES.O)
    fun getBitmapFromView(view: View, activity: Activity, callback: (Bitmap) -> Unit) {
        activity.window?.let { window ->
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val locationOfViewInWindow = IntArray(2)
            view.getLocationInWindow(locationOfViewInWindow)
            try {
                PixelCopy.request(window, Rect(locationOfViewInWindow[0], locationOfViewInWindow[1], locationOfViewInWindow[0] + view.width, locationOfViewInWindow[1] + view.height), bitmap, { copyResult ->
                    if (copyResult == PixelCopy.SUCCESS) {
                        callback(bitmap)
                    }
                    // possible to handle other result codes ...
                }, Handler())
            } catch (e: IllegalArgumentException) {
                // PixelCopy may throw IllegalArgumentException, make sure to handle it
                e.printStackTrace()
            }
        }
    }*/

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun blur(context: Context, image: Bitmap?): Bitmap? {
        var outputBitmap: Bitmap? = null

        if (image != null) {

            if (BLUR_RADIUS == 0f) {
                return image
            }

            if (BLUR_RADIUS < 1) {
                BLUR_RADIUS = 1f
            }

            if (BLUR_RADIUS > 25) {
                BLUR_RADIUS = 25f
            }

            val width = (image.width * BITMAP_SCALE).roundToInt()
            val height = (image.height * BITMAP_SCALE).roundToInt()

            val inputBitmap = Bitmap.createScaledBitmap(image, width, height, false)
            outputBitmap = Bitmap.createBitmap(inputBitmap)

            val rs = RenderScript.create(context)
            val theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            val tmpIn = Allocation.createFromBitmap(rs, inputBitmap)
            val tmpOut = Allocation.createFromBitmap(rs, outputBitmap)
            theIntrinsic.setRadius(BLUR_RADIUS)
            theIntrinsic.setInput(tmpIn)
            theIntrinsic.forEach(tmpOut)
            tmpOut.copyTo(outputBitmap)
        }
        return outputBitmap
    }
}