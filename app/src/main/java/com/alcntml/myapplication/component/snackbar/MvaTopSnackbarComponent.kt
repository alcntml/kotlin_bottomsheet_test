package com.alcntml.myapplication.component.snackbar

import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import com.alcntml.myapplication.R
import com.alcntml.myapplication.extention.setSafeOnClickListener
import kotlinx.android.synthetic.main.mva_top_snackbar_component.view.*
import android.view.ViewGroup
import java.lang.Exception

class MvaTopSnackbarComponent : CoordinatorLayout {

    private val LONG_MILLIS = 4000L
    private val SHORT_MILLIS = 2000L

    companion object {
        public val LONG = 0
        public val SHORT = 1
    }

    private lateinit var topSheetBehavior: TopSheetBehavior<FrameLayout>
    private var isCancelable = false
    private var currentDuration = LONG
    private var mHandler: Handler? = null

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
        LayoutInflater.from(context).inflate(R.layout.mva_top_snackbar_component, this, true)
        layoutParams = CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,CoordinatorLayout.LayoutParams.WRAP_CONTENT)
        topSheetBehavior = TopSheetBehavior.from(sheetFL)
        topSheetBehavior.setTopSheetCallback(topSheetCallback)
        topSheetBehavior.state = TopSheetBehavior.STATE_COLLAPSED
    }

    public fun showDialog(message: String){
        show(message,true, LONG)
    }

    public fun showDialog(message: String, duration: Int){
        show(message,true,duration)
    }

    public fun showSnack(message: String){
        show(message,false, LONG)
    }

    public fun showSnack(message: String, duration: Int){
        show(message,false,duration)
    }

    private fun show(message: String, isCancelable: Boolean, duration: Int) {
        this.isCancelable = isCancelable
        currentDuration = duration
        messageTV.text = message
        if (isCancelable){
            /*overlayInvisibleV.setSafeOnClickListener{
                dismiss()
            }*/
            closeIV.setSafeOnClickListener{
                dismiss()
            }
            closeIV.visibility = View.VISIBLE
        }else{
            /*overlayInvisibleV.setOnClickListener(null)*/
            closeIV.setOnClickListener(null)
            closeIV.visibility = View.GONE
        }
        if (topSheetBehavior.state == TopSheetBehavior.STATE_COLLAPSED) {
            topSheetBehavior.state = TopSheetBehavior.STATE_EXPANDED
        }
    }

    private fun dismiss() {
        if (topSheetBehavior.state == TopSheetBehavior.STATE_EXPANDED) {
            topSheetBehavior.state = TopSheetBehavior.STATE_COLLAPSED
        }
        if(mHandler != null){
            mHandler!!.removeCallbacks(mRunnable)
        }
    }

    private var topSheetCallback: TopSheetBehavior.TopSheetCallback = object :
        TopSheetBehavior.TopSheetCallback() {
        override fun onStateChanged(bottomSheet: View, state: Int) {
            when (state) {
                TopSheetBehavior.STATE_HIDDEN -> {
                }
                TopSheetBehavior.STATE_EXPANDED -> {
                    onExpanded()
                }
                TopSheetBehavior.STATE_COLLAPSED -> {
                }
                TopSheetBehavior.STATE_DRAGGING -> {
                }
                TopSheetBehavior.STATE_SETTLING -> {
                }
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float, isOpening: Boolean?) {
            setOverlay(slideOffset)
        }

    }

    private fun onExpanded() {
        var duration = SHORT_MILLIS
        if (currentDuration == LONG) {
            duration = LONG_MILLIS
        }
        if (!isCancelable) {
            mHandler = Handler()
            if (mHandler != null) {
                mHandler!!.postDelayed(mRunnable, duration)
            }
        }
    }

    private fun setOverlay(offset: Float) {
        overlayV.alpha = offset
        /*overlayInvisibleV.visibility = View.VISIBLE*/
        if (offset == 0.0f) {
            hideOverlay()
            /*overlayInvisibleV.visibility = View.GONE*/
        }else{
            showOverlay()
        }
    }

    private fun showOverlay(){
        if (!overlayV.isVisible) {
            overlayV.visibility = View.VISIBLE
            Handler().postDelayed({
                overlayV.setSafeOnClickListener {
                    dismiss()
                }
            }, 300)
        }
    }

    private fun hideOverlay(){
        if (overlayV.isVisible) {
            overlayV.setOnClickListener(null)
            overlayV.visibility = View.GONE
        }
    }

    private var mRunnable: Runnable = Runnable { dismiss() }
}