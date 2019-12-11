package com.alcntml.myapplication.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alcntml.myapplication.R

public class BottomSheetFragment4 : Fragment() {

    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.bottom_sheet4, container, false)
        }
        return rootView

    }
}