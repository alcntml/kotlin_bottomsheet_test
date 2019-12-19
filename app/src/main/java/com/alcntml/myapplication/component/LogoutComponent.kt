package com.alcntml.myapplication.component

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.alcntml.myapplication.R
import com.alcntml.myapplication.extention.setSafeOnClickListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.component_logout.view.*

class LogoutComponent : CoordinatorLayout {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private var onLogoutListener: OnLogoutListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.component_logout, this, true)
        val params: CoordinatorLayout.LayoutParams = CoordinatorLayout.LayoutParams(
            CoordinatorLayout.LayoutParams.MATCH_PARENT,
            CoordinatorLayout.LayoutParams.WRAP_CONTENT
        )
        params.gravity = Gravity.BOTTOM
        layoutParams = params
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetFL)
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        closeIV.setSafeOnClickListener {
            dismiss()
        }
        negativeBtn.setSafeOnClickListener {
            dismiss()
        }
        positiveBtn.setSafeOnClickListener {
            onLogoutListener?.onLogout()
        }
    }

    public fun show(onLogoutListener: OnLogoutListener) {
        this.onLogoutListener = onLogoutListener
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    public fun dismiss() {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback = object :
        BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, state: Int) {
            when (state) {
                BottomSheetBehavior.STATE_HIDDEN -> {
                }
                BottomSheetBehavior.STATE_EXPANDED -> {
                }
                BottomSheetBehavior.STATE_COLLAPSED -> {
                }
                BottomSheetBehavior.STATE_DRAGGING -> {
                }
                BottomSheetBehavior.STATE_SETTLING -> {
                }
                BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                }
            }
        }

        override fun onSlide(view: View, v: Float) {
            setOverlay(v)
        }

    }

    private fun setOverlay(offset: Float) {
        overlayV.alpha = offset
        bottomSheetIndicator.alpha = offset
        bottomSheetIndicator.visibility = View.VISIBLE
        if (offset == 0.0f) {
            hideOverlay()
            bottomSheetIndicator.visibility = View.INVISIBLE
        } else {
            showOverlay()
        }
    }

    private fun showOverlay() {
        overlayV.visibility = View.VISIBLE
        Handler().postDelayed({
            overlayV.setSafeOnClickListener {
                dismiss()
            }
        }, 300)
    }

    private fun hideOverlay() {
        overlayV.setOnClickListener(null)
        overlayV.visibility = View.GONE
    }

    public interface OnLogoutListener {
        fun onLogout()
    }
}