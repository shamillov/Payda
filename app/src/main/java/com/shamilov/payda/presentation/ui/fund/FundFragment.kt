package com.shamilov.payda.presentation.ui.fund

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.AppBarLayout
import com.shamilov.payda.R

/**
 * Created by Shamilov on 20.05.2020
 */
class FundFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_fund, container, false)

        return root
    }
}
