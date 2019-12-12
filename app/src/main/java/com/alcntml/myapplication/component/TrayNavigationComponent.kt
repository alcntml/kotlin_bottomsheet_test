package com.alcntml.myapplication.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentManager
import com.alcntml.myapplication.R
import com.alcntml.myapplication.`interface`.TrayCallback
import com.alcntml.myapplication.fragment.BottomSheetFragment1
import com.alcntml.myapplication.fragment.BottomSheetFragment2
import com.alcntml.myapplication.fragment.BottomSheetFragment3
import com.alcntml.myapplication.fragment.BottomSheetFragment4
import com.alcntml.myapplication.util.TrayHelper
import com.alcntml.myapplication.util.TrayModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.mva_bottom_navigation.view.*

class TrayNavigationComponent: CoordinatorLayout{

    private var trayModel: TrayModel? = null;
    private var supportFragmentManager: FragmentManager? = null

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
        LayoutInflater.from(context).inflate(R.layout.mva_bottom_navigation,this,true)

        trayModel = TrayModel(
                arrayListOf(
                    BottomSheetBehavior.from(bottom_sheet1)
                    , BottomSheetBehavior.from(bottom_sheet2)
                    , BottomSheetBehavior.from(bottom_sheet3)
                    , BottomSheetBehavior.from(bottom_sheet4)
                ), arrayListOf(
                    tabItem1
                    , tabItem2
                    , tabItem3
                    , tabItem4
                ), arrayListOf(
                    overlay1
                    , overlay2
                    , overlay3
                    , overlay4
                )
                , trayCallback
            )
    }

    public fun setFragmentManager(supportFragmentManager: FragmentManager){
        this.supportFragmentManager = supportFragmentManager
    }

    public fun initView() {
        //set tab visibility and fragment according to response
        if (supportFragmentManager != null) {
            supportFragmentManager?.beginTransaction().let {
                it!!.replace(R.id.bottom_sheet1, BottomSheetFragment1.newInstance(0))
                it!!.replace(R.id.bottom_sheet2, BottomSheetFragment2())
                it!!.replace(R.id.bottom_sheet3, BottomSheetFragment3())
                it!!.replace(R.id.bottom_sheet4, BottomSheetFragment4())
                it!!.commit()
            }
        }
        trayModel?.let { TrayHelper.getInstance(context).setMenu(it) }
    }

    private var trayCallback: TrayCallback = object :
        TrayCallback {
        override fun onStateChange(view: View, state: Int, position: Int) {
            when (state) {
                BottomSheetBehavior.STATE_HIDDEN -> {
                    Log.i("TRAY", "STATE_HIDDEN  Position:$position");
                }
                BottomSheetBehavior.STATE_EXPANDED -> {
                    Log.i("TRAY", "STATE_EXPANDED  Position:$position");
                }
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    Log.i("TRAY", "STATE_COLLAPSED  Position:$position");
                }
                BottomSheetBehavior.STATE_DRAGGING -> {
                    Log.i("TRAY", "STATE_DRAGGING  Position:$position");
                }
                BottomSheetBehavior.STATE_SETTLING -> {
                    Log.i("TRAY", "STATE_SETTLING  Position:$position");
                }
                BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    Log.i("TRAY", "STATE_HALF_EXPANDED  Position:$position");
                }
            }
        }

        override fun onSlide(view: View, v: Float, position: Int) {
            Log.i("TRAY", "onSlide  Offset:$v");
        }

    }
}