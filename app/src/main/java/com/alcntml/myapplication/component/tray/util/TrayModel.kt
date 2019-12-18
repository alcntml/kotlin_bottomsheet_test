package com.alcntml.myapplication.component.tray.util

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.alcntml.myapplication.component.tray.`interface`.TrayCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior

data class TrayModel(
    var bottomSheetBehaviorList: ArrayList<BottomSheetBehavior<FrameLayout>>
    , var tabList: ArrayList<View>
    , var overlayList: ArrayList<View>
    , var trayCallback: TrayCallback
)