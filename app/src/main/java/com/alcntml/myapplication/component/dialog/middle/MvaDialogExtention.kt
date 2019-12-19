package com.alcntml.myapplication.component.dialog.middle

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import com.alcntml.myapplication.component.dialog.middle.MvaAlertDialog

fun Activity.alert(
    context: Context,
    title: String? = null,
    message: String? = null,
    positiveButton: String? = null,
    negativeButton: String? = null,
    positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    cancelListener: DialogInterface.OnCancelListener? = null
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener
    ).show()
}

fun Activity.alertWithDelay(delay: Long? = 0L,
                            context: Context,
                            title: String? = null,
                            message: String? = null,
                            positiveButton: String? = null,
                            negativeButton: String? = null,
                            positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                            negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                            cancelListener: DialogInterface.OnCancelListener? = null
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener
    ).showWithDelay(delay!!)
}

fun Activity.alert(
    context: Context,
    title: String? = null,
    message: String? = null,
    positiveButton: String? = null,
    negativeButton: String? = null,
    positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    cancelListener: DialogInterface.OnCancelListener? = null,
    cancelable: Boolean = true
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener,
        cancelable
    ).show()
}

fun Activity.alertWithDelay(delay: Long? = 0L,
                            context: Context,
                            title: String? = null,
                            message: String? = null,
                            positiveButton: String? = null,
                            negativeButton: String? = null,
                            positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                            negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                            cancelListener: DialogInterface.OnCancelListener? = null,
                            cancelable: Boolean = true
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener,
        cancelable
    ).showWithDelay(delay!!)
}

fun Fragment.alert(
    context: Context,
    title: String? = null,
    message: String? = null,
    positiveButton: String? = null,
    negativeButton: String? = null,
    positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    cancelListener: DialogInterface.OnCancelListener? = null
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener
    ).show()
}

fun Fragment.alertWithDelay(delay: Long? = 0L,
                            context: Context,
                            title: String? = null,
                            message: String? = null,
                            positiveButton: String? = null,
                            negativeButton: String? = null,
                            positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                            negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                            cancelListener: DialogInterface.OnCancelListener? = null
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener
    ).showWithDelay(delay!!)
}

fun Fragment.alert(
    context: Context,
    title: String? = null,
    message: String? = null,
    positiveButton: String? = null,
    negativeButton: String? = null,
    positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    cancelListener: DialogInterface.OnCancelListener? = null,
    cancelable: Boolean = true
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener,
        cancelable
    ).show()
}

fun Fragment.alertWithDelay(delay: Long? = 0L,
                            context: Context,
                            title: String? = null,
                            message: String? = null,
                            positiveButton: String? = null,
                            negativeButton: String? = null,
                            positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                            negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                            cancelListener: DialogInterface.OnCancelListener? = null,
                            cancelable: Boolean = true
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener,
        cancelable
    ).showWithDelay(delay!!)
}

fun Dialog.alert(
    context: Context,
    title: String? = null,
    message: String? = null,
    positiveButton: String? = null,
    negativeButton: String? = null,
    positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    cancelListener: DialogInterface.OnCancelListener? = null
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener
    ).show()
}

fun Dialog.alertWithDelay(delay: Long? = 0L,
                          context: Context,
                          title: String? = null,
                          message: String? = null,
                          positiveButton: String? = null,
                          negativeButton: String? = null,
                          positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                          negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                          cancelListener: DialogInterface.OnCancelListener? = null
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener
    ).showWithDelay(delay!!)
}

fun Dialog.alert(
    context: Context,
    title: String? = null,
    message: String? = null,
    positiveButton: String? = null,
    negativeButton: String? = null,
    positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
    cancelListener: DialogInterface.OnCancelListener? = null,
    cancelable: Boolean = true
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener,
        cancelable
    ).show()
}

fun Dialog.alertWithDelay(delay: Long? = 0L,
                          context: Context,
                          title: String? = null,
                          message: String? = null,
                          positiveButton: String? = null,
                          negativeButton: String? = null,
                          positiveButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                          negativeButtonListener: MvaAlertDialog.OnDialogButtonClickListener? = null,
                          cancelListener: DialogInterface.OnCancelListener? = null,
                          cancelable: Boolean = true
): Unit {
    return MvaAlertDialog(
        context, title,
        message,
        positiveButton,
        negativeButton,
        positiveButtonListener,
        negativeButtonListener,
        cancelListener,
        cancelable
    ).showWithDelay(delay!!)
}