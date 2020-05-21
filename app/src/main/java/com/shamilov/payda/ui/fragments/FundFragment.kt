package com.shamilov.payda.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.shamilov.payda.R

class FundFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fund, container, false)
        val appBarLayout = activity?.findViewById<AppBarLayout>(R.id.toolbarLayout)
        appBarLayout?.elevation = 5F

        return root
    }
}
