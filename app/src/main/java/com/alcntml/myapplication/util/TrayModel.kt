package com.alcntml.myapplication.util

import android.os.Parcelable
import android.view.View
import android.widget.FrameLayout
import com.alcntml.myapplication.`interface`.TrayCallback
import com.alcntml.myapplication.util.TrayHelper
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.parcel.Parcelize

data class TrayModel(
    var bottomSheetBehaviorList: ArrayList<BottomSheetBehavior<FrameLayout>>
    , var tabList: ArrayList<View>
    , var overlayList: ArrayList<View>
    , var trayCallback: TrayCallback
)