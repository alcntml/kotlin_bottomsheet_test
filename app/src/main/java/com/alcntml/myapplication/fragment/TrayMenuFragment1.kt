package com.alcntml.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alcntml.myapplication.R
import com.alcntml.myapplication.extention.setSafeOnClickListener
import com.alcntml.myapplication.component.tray.util.TrayHelper
import kotlinx.android.synthetic.main.fragment_tray_menu1.*

public class TrayMenuFragment1 : Fragment() {

    private var rootView: View? = null
    private var position: Int = -1

    companion object {
        fun newInstance(position: Int): TrayMenuFragment1 {
            val b: Bundle = Bundle()
            b.putInt("position",position)
            val fragment = TrayMenuFragment1()
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
            rootView = inflater.inflate(R.layout.fragment_tray_menu1, container, false)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        position = arguments!!.getInt("position")
        closeTV.setSafeOnClickListener{
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