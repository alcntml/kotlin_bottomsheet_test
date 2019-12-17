package com.alcntml.myapplication.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.alcntml.myapplication.R
import com.alcntml.myapplication.extention.setSafeOnClickListener
import com.alcntml.myapplication.util.BlurUtil
import com.alcntml.myapplication.util.async.BlurTask
import com.alcntml.myapplication.util.async.interfaces.AsyncBlurListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.mva_logut_component.view.*
import java.lang.Exception

class MvaLogoutComponent : CoordinatorLayout {

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private var onLogoutListener: OnLogoutListener? = null
    private var blurTask: BlurTask? = null
    private var blurView: View? = null

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
        LayoutInflater.from(context).inflate(R.layout.mva_logut_component, this, true)
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

    public fun setBlurView(blurView: View?){
        this.blurView = blurView
        if (blurView != null) {
            blurTask = BlurTask(context, blurView, onBlurListener)
        }
    }

    public fun show(onLogoutListener: OnLogoutListener) {
        this.onLogoutListener = onLogoutListener
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            /*controlBlur(context)*/
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun dismiss() {
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

    private fun controlBlur(context: Context) {
        //Normally version can be "Build.VERSION_CODES.JELLY_BEAN_MR1" but we apply "Build.VERSION_CODES.M" becouse of performans issues
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && blurTask != null) {
            if (blurView != null) {
                blurTask = BlurTask(context, blurView!!, onBlurListener)
                blurTask!!.execute()
            }
        }
    }

    private var onBlurListener = object :
        AsyncBlurListener {
        override fun onLoad(bitmap: Bitmap?) {
            if (bitmap != null) {
                overlayBlurV.background = BitmapDrawable(context.resources, bitmap);
            }
        }
    }

    private fun setOverlay(offset: Float) {
        overlayV.alpha = offset
        overlayBlurV.alpha = offset
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
        overlayBlurV.visibility = View.VISIBLE
        Handler().postDelayed({
            overlayV.setSafeOnClickListener {
                dismiss()
            }
            overlayBlurV.setSafeOnClickListener {
                dismiss()
            }
        }, 300)
    }

    private fun hideOverlay() {
        overlayV.setOnClickListener(null)
        overlayBlurV.setOnClickListener(null)
        overlayV.visibility = View.GONE
        overlayBlurV.visibility = View.GONE
    }

    public interface OnLogoutListener {
        fun onLogout()
    }
}