package com.alcntml.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alcntml.myapplication.R

public class BottomSheetFragment2 : Fragment() {

    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.bottom_sheet2, container, false)
        }
        return rootView

    }

    override fun onStop() {
        super.onStop()
        Log.i("BottomSheetFragment2", "onStop")
    }

    override fun onStart() {
        super.onStart()
        Log.i("BottomSheetFragment2", "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("BottomSheetFragment2", "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i("BottomSheetFragment2", "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("BottomSheetFragment2", "onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i("BottomSheetFragment2", "onDestroyView")
    }
}