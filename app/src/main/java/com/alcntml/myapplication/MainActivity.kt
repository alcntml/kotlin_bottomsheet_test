package com.alcntml.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetBehavior
import android.view.View
import com.alcntml.myapplication.fragment.BottomSheetFragment1
import com.alcntml.myapplication.fragment.BottomSheetFragment2
import com.alcntml.myapplication.fragment.BottomSheetFragment3
import com.alcntml.myapplication.fragment.BottomSheetFragment4
import com.alcntml.myapplication.util.TrayHelper
import com.alcntml.myapplication.util.TrayModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.mva10_bottom_navigation.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar);
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener { finish() }

        //add response to component for create navigationbar and fragments
        trayNavComp.setFragmentManager(supportFragmentManager)
        trayNavComp.initView()
    }
}
