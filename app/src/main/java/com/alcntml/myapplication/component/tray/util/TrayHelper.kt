package com.alcntml.myapplication.component.tray.util

import android.content.Context
import android.os.Handler
import android.os.SystemClock
import android.view.View

import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.alcntml.myapplication.util.SingletonHolder
import kotlinx.android.synthetic.main.component_logout.view.*

class TrayHelper private constructor(context: Context) {

    private var trayModel: TrayModel? = null
    private var lastTimeClicked: Long = 0
    private val mContext = context

    companion object : SingletonHolder<TrayHelper, Context>(::TrayHelper)

    public fun setMenu(trayModel: TrayModel){
        this.trayModel = trayModel
        for (i in 0 until trayModel.bottomSheetBehaviorList.size) {
            setMenuItem(i)
        }
    }

    private fun setMenuItem(position: Int) {
        trayModel!!.bottomSheetBehaviorList[position].saveFlags = BottomSheetBehavior.SAVE_ALL
        trayModel!!.tabList[position].setOnClickListener {
            if (SystemClock.elapsedRealtime() - lastTimeClicked < 500) {
                return@setOnClickListener
            }
            lastTimeClicked = SystemClock.elapsedRealtime()
            for (j in 0 until trayModel!!.bottomSheetBehaviorList.size) {
                if (j != position) {
                    collapse(j)
                }
            }
            if (trayModel!!.bottomSheetBehaviorList[position].state != BottomSheetBehavior.STATE_EXPANDED) {
                Handler().postDelayed({
                    expand(position)
                }, 100)
            } else {
                collapse(position)
            }
        }
        trayModel!!.bottomSheetBehaviorList[position].addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(view: View, state: Int) {
                trayModel!!.trayCallback.onStateChange(view, state, position)
            }

            override fun onSlide(view: View, v: Float) {
                trayModel!!.trayCallback.onSlide(view, v, position)
                setOverlay(v, position)
            }
        })
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
            trayModel!!.bottomSheetBehaviorList[position].state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun setOverlay(v: Float, position: Int) {
        trayModel!!.overlayList[position].alpha = v
        if (v == 0.0f) {
            hideOverlay(position)
        } else {
            showOverlay(position)
        }
    }

    private fun showOverlay(position: Int) {
        trayModel!!.overlayList[position].visibility = View.VISIBLE
        Handler().postDelayed({
            trayModel!!.overlayList[position].setOnClickListener {
                collapseAll()
            }
        }, 300)
    }

    private fun hideOverlay(position: Int) {
        trayModel!!.overlayList[position].setOnClickListener(null)
        trayModel!!.overlayList[position].visibility = View.GONE
    }
}
