package com.shamilov.payda.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.shamilov.payda.R

/**
 * Created by Shamilov on 20.05.2020
 */
class ProfileFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        val appBarLayout = activity?.findViewById<AppBarLayout>(R.id.toolbarLayout)
        appBarLayout?.elevation = 5F

        val tvContact: TextView = root.findViewById(R.id.tvSendUs)
        tvContact.setOnClickListener(this)

        return root
    }

    override fun onClick(v: View?) {
        findNavController().navigate(R.id.navigation_contact)
    }
}
