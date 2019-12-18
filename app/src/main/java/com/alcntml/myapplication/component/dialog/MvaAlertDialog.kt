package com.alcntml.myapplication.component.dialog

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Handler
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.alcntml.myapplication.R

public class MvaAlertDialog(
    private val context: Context,
    private val title: String? = null,
    private val message: String? = null,
    private val positiveButton: String? = null,
    private val negativeButton: String? = null,
    private val positiveButtonListener: OnDialogButtonClickListener? = null,
    private val negativeButtonListener: OnDialogButtonClickListener? = null,
    private val cancelable: Boolean = true) {

    private var dialogView: Dialog? = null

    private var titleTV: TextView? = null
    private var messageTV: TextView? = null
    private var negativeBtn: Button? = null
    private var positiveBtn: Button? = null
    private var overlayV: View? = null
    private var contentCV: CardView? = null

    private var backPressed = false

    public fun showWithDelay(delay: Long){
        dialogView = Dialog(context, R.style.MvaAlertDialogTheme)
        dialogView!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val layout: Int = R.layout.dialog_alert_mva

        dialogView!!.setContentView(layout)
        dialogView!!.window!!.attributes.windowAnimations = R.style.MvaAlertDialogAnimation
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialogView!!.window!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            dialogView!!.window!!.statusBarColor = ContextCompat.getColor(context,R.color.colorPrimary);
        }
        dialogView!!.setCancelable(cancelable)
        dialogView!!.setCanceledOnTouchOutside(cancelable)

        titleTV = dialogView!!.findViewById<TextView>(R.id.titleTV)
        messageTV = dialogView!!.findViewById<TextView>(R.id.messageTV)
        negativeBtn = dialogView!!.findViewById<Button>(R.id.negativeBtn)
        positiveBtn = dialogView!!.findViewById<Button>(R.id.positiveBtn)
        overlayV = dialogView!!.findViewById<View>(R.id.overlayV)
        contentCV = dialogView!!.findViewById<CardView>(R.id.contentCV)

        dialogView!!.setOnDismissListener{
            startDismissAnimation()
        }
        dialogView!!.setOnKeyListener{ dialogInterface: DialogInterface, i: Int, keyEvent: KeyEvent ->
            if (i == KeyEvent.KEYCODE_BACK && !backPressed) {
                backPressed = true
                dismiss()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        setTitle()
        setMessage()
        setNegativeButton()
        setPositiveButton()
        setOutside()

        Handler().postDelayed({
            if (!(context as Activity).isFinishing && dialogView != null) {
                startShowAnimation()
            }
        },delay)
    }

    public fun show(){
        showWithDelay(0L)
    }

    private fun dismiss(){
        startDismissAnimation()
    }

    private fun setTitle(){
        if (title != null){
            titleTV!!.text = title
            titleTV!!.visibility = View.VISIBLE
        }else{
            titleTV!!.visibility = View.GONE
        }
    }

    private fun setMessage(){
        if (message != null){
            messageTV!!.text = message
            messageTV!!.visibility = View.VISIBLE
        }else{
            messageTV!!.visibility = View.GONE
        }
    }

    private fun setNegativeButton(){
        if (negativeButton != null){
            negativeBtn!!.text = negativeButton
            negativeBtn!!.visibility = View.VISIBLE
        }else{
            negativeBtn!!.visibility = View.GONE
        }

        if (negativeButtonListener != null){
            negativeBtn!!.setOnClickListener{
                dismiss()
                negativeButtonListener.onClick()
            }
        }
    }

    private fun setPositiveButton(){
        if (positiveButton != null){
            positiveBtn!!.text = positiveButton
            positiveBtn!!.visibility = View.VISIBLE
        }else{
            positiveBtn!!.visibility = View.GONE
        }

        if (positiveButtonListener != null){
            positiveBtn!!.setOnClickListener{
                dismiss()
                positiveButtonListener.onClick()
            }
        }
    }

    private fun setOutside(){
        overlayV!!.setOnClickListener{
            dismiss()
        }
    }

    private fun startShowAnimation(){
        dialogView!!.show()

        val animatorContentScaleX = ObjectAnimator.ofFloat(contentCV, "scaleX", 0f, 1f)
        val animatorContentScaleY = ObjectAnimator.ofFloat(contentCV, "scaleY", 0f, 1f)
        val animatorContentAlpha = ObjectAnimator.ofFloat(contentCV, "alpha", 0f, 1f)
        val animatorOverlayAlpha = ObjectAnimator.ofFloat(overlayV, "alpha", 0f, 1f)

        val animationSet: AnimatorSet = AnimatorSet()
        animationSet.duration = 300
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.setupEndValues()
        animationSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
            }
        })
        animationSet.playTogether(animatorContentScaleX,animatorContentScaleY,animatorContentAlpha,animatorOverlayAlpha)
        animationSet.start()
    }

    private fun startDismissAnimation(){
        val animatorContentScaleX = ObjectAnimator.ofFloat(contentCV, "scaleX", 1f, 0f)
        val animatorContentScaleY = ObjectAnimator.ofFloat(contentCV, "scaleY", 1f, 0f)
        val animatorContentAlpha = ObjectAnimator.ofFloat(contentCV, "alpha", 1f, 0f)
        val animatorOverlayAlpha = ObjectAnimator.ofFloat(overlayV, "alpha", 1f, 0f)

        val animationSet: AnimatorSet = AnimatorSet()
        animationSet.duration = 200
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.setupEndValues()
        animationSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                dialogView!!.dismiss()
            }
        })

        animationSet.playTogether(animatorContentScaleX,animatorContentScaleY,animatorContentAlpha,animatorOverlayAlpha)
        animationSet.start()
    }

    public interface OnDialogButtonClickListener{
        fun onClick()
    }
}