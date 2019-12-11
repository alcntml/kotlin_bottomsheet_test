package com.alcntml.myapplication.util

import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.FrameLayout

import com.google.android.material.bottomsheet.BottomSheetBehavior

class TrayHelper private constructor(context: Context) {

    private var trayModel: TrayModel? = null

    companion object : SingletonHolder<TrayHelper, Context>(::TrayHelper)

    fun setMenu(trayModel: TrayModel) {
        this.trayModel = trayModel
        for (i in 0 until trayModel.tabList.size) {
            trayModel.bottomSheetBehaviorList[i].saveFlags = BottomSheetBehavior.SAVE_ALL
            val finalI = i
            trayModel.tabList[i].setOnClickListener {
                for (j in 0 until trayModel.bottomSheetBehaviorList.size) {
                    if (j != finalI) {
                        collapse(j)
                    }
                }
                if (trayModel.bottomSheetBehaviorList[finalI].state != BottomSheetBehavior.STATE_EXPANDED) {
                    Handler().postDelayed({
                        expand(finalI)
                    },100)
                }else{
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
                    trayModel.overlayList[finalI].alpha = v
                    if (v == 0.0f) {
                        trayModel.overlayList[finalI].visibility = View.GONE
                    } else {
                        trayModel.overlayList[finalI].visibility = View.VISIBLE
                    }
                }
            })
            trayModel.overlayList[i].setOnClickListener { collapseAll() }
        }
    }

    fun collapseAll() {
        for (j in 0 until trayModel!!.bottomSheetBehaviorList.size) {
            collapse(j)
        }
    }

    fun collapse(position: Int) {
        trayModel!!.bottomSheetBehaviorList[position].state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun expand(position: Int) {
        trayModel!!.bottomSheetBehaviorList[position].state = BottomSheetBehavior.STATE_EXPANDED
    }
}
