package com.alcntml.myapplication.component.snackbar

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.alcntml.myapplication.R
import com.alcntml.myapplication.extention.setSafeOnClickListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.mva_top_snackbar_component.view.*

class MvaBottomSnackbarComponent : CoordinatorLayout {

    private val LONG_MILLIS = 4000L
    private val SHORT_MILLIS = 2000L

    companion object{
        public val LONG = 0 //default
        public val SHORT = 1
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private var isCancelable = false
    private var currentDuration = LONG
    private var mHandler: Handler? = null

    constructor(context: Context) : super(context) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init(){
        LayoutInflater.from(context).inflate(R.layout.mva_bottom_snackbar_component,this,true)
        layoutParams = CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT,CoordinatorLayout.LayoutParams.WRAP_CONTENT)

        bottomSheetBehavior = BottomSheetBehavior.from(sheetFL)
        bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

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

    private fun show(message: String, isCancelable: Boolean, duration: Int){
        this.isCancelable = isCancelable
        currentDuration = duration
        messageTV.text = message
        if (isCancelable){
            /*overlayInvisibleV.setOnClickListener{
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
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    private fun dismiss(){
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
        if(mHandler != null){
            mHandler!!.removeCallbacks(mRunnable)
        }
    }

    private var bottomSheetCallback: BottomSheetBehavior.BottomSheetCallback = object :
        BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, state: Int) {
            when (state) {
                BottomSheetBehavior.STATE_HIDDEN -> {
                }
                BottomSheetBehavior.STATE_EXPANDED -> {
                    onExpanded()
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
        overlayV.visibility = View.VISIBLE
        Handler().postDelayed({
            overlayV.setSafeOnClickListener{
                dismiss()
            }
        },300)
    }

    private fun hideOverlay(){
        overlayV.setOnClickListener(null)
        overlayV.visibility = View.GONE
    }

    private var mRunnable: Runnable = Runnable { dismiss() }
}