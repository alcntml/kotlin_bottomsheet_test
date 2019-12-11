package com.alcntml.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.alcntml.myapplication.R
import com.alcntml.myapplication.util.TrayHelper
import com.alcntml.myapplication.util.TrayModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.bottom_sheet1.*

public class BottomSheetFragment1 : Fragment() {

    private var rootView: View? = null
    private var position: Int = -1

    companion object {
        fun newInstance(position: Int): BottomSheetFragment1 {
            val b: Bundle = Bundle()
            b.putInt("position",position)
            val fragment = BottomSheetFragment1()
            fragment.arguments = b
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.bottom_sheet1, container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        position = arguments!!.getInt("position")
        closeTV.setOnClickListener{
            if (context != null) {
                TrayHelper.getInstance(context!!).collapse(position)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i("BottomSheetFragment1", "onStop")
    }

    override fun onStart() {
        super.onStart()
        Log.i("BottomSheetFragment1", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("BottomSheetFragment1", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i("BottomSheetFragment1", "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("BottomSheetFragment1", "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("BottomSheetFragment1", "onDestroyView")
    }
}