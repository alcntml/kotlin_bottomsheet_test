package com.alcntml.myapplication.component.tray.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Handler
import android.os.SystemClock
import android.view.View
import com.alcntml.myapplication.util.async.interfaces.AsyncBlurListener
import com.alcntml.myapplication.util.async.BlurTask

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.alcntml.myapplication.util.SingletonHolder


class TrayHelper private constructor(context: Context) {

    private var trayModel: TrayModel? = null
    private var blurView: View? = null
    private var blurTask: BlurTask? = null
    private var lastTimeClicked: Long = 0
    private val mContext = context

    companion object : SingletonHolder<TrayHelper, Context>(::TrayHelper)

    public fun setBlurView(context: Context,blurView: View?){
        this.blurView = blurView
        if (blurView != null) {
            blurTask = BlurTask(context, blurView, onBlurListener)
        }
    }

    fun setMenu(trayModel: TrayModel) {
        this.trayModel = trayModel
        for (i in 0 until trayModel.tabList.size) {
            trayModel.bottomSheetBehaviorList[i].saveFlags = BottomSheetBehavior.SAVE_ALL
            val finalI = i
            trayModel.tabList[i].setOnClickListener {
                if (SystemClock.elapsedRealtime() - lastTimeClicked < 500) {
                    return@setOnClickListener
                }
                lastTimeClicked = SystemClock.elapsedRealtime()
                for (j in 0 until trayModel.bottomSheetBehaviorList.size) {
                    if (j != finalI) {
                        collapse(j)
                    }
                }
                if (trayModel.bottomSheetBehaviorList[finalI].state != BottomSheetBehavior.STATE_EXPANDED) {
                    Handler().postDelayed({
                        expand(finalI)
                    }, 100)
                } else {
                    collapse(finalI)
                }
            }
            trayModel.bottomSheetBehaviorList[i].addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(view: View, state: Int) {
                    trayModel.trayCallback.onStateChange(view, state, finalI)
                }

                override fun onSlide(view: View, v: Float) {
                    trayModel.trayCallback.onSlide(view, v, finalI)
                    setOverlay(v, finalI)
                }
            })
        }
    }

    fun collapseAll() {
        for (j in 0 until trayModel!!.bottomSheetBehaviorList.size) {
            collapse(j)
        }
    }

    fun collapse(position: Int) {
        if (trayModel!!.bottomSheetBehaviorList[position].state == BottomSheetBehavior.STATE_EXPANDED) {
            trayModel!!.bottomSheetBehaviorList[position].state =
                BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    fun expand(position: Int) {
        if (trayModel!!.bottomSheetBehaviorList[position].state == BottomSheetBehavior.STATE_COLLAPSED) {
            blurTask!!.setPosition(position)
            /*controlBlur(mContext, blurView!!, position)*/
            trayModel!!.bottomSheetBehaviorList[position].state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun controlBlur(context: Context, blurView: View, position: Int){
        //Normally version can be "Build.VERSION_CODES.JELLY_BEAN_MR1" but we apply "Build.VERSION_CODES.M" becouse of performans issues
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && blurTask != null) {
            blurTask = BlurTask(context, blurView, onBlurListener)
            blurTask!!.setPosition(position)
            blurTask!!.execute()
        }
    }

    private var onBlurListener = object :
        AsyncBlurListener {
        override fun onLoad(bitmap: Bitmap?) {
            if (bitmap != null && blurTask != null) {
                trayModel!!.blurOverlayList[blurTask!!.getPosition()].background = BitmapDrawable(context.resources, bitmap);
            }
        }
    }

    private fun setOverlay(v: Float, position: Int) {
        trayModel!!.overlayList[position].alpha = v
        trayModel!!.blurOverlayList[position].alpha = v
        if (v == 0.0f) {
            hideOverlay(position)
        } else {
            showOverlay(position)
        }
    }

    private fun showOverlay(position: Int) {
        trayModel!!.overlayList[position].visibility = View.VISIBLE
        trayModel!!.blurOverlayList[position].visibility = View.VISIBLE
        Handler().postDelayed({
            trayModel!!.overlayList[position].setOnClickListener {
                collapseAll()
            }
            trayModel!!.blurOverlayList[position].setOnClickListener {
                collapseAll()
            }
        }, 300)
    }

    private fun hideOverlay(position: Int) {
        trayModel!!.overlayList[position].setOnClickListener(null)
        trayModel!!.blurOverlayList[position].setOnClickListener(null)
        trayModel!!.overlayList[position].visibility = View.GONE
        trayModel!!.blurOverlayList[position].visibility = View.GONE
    }
}
