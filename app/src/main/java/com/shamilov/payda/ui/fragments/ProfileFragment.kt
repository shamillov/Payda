package com.shamilov.payda.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.shamilov.payda.R

class ProfileFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

        var textView: TextView = root.findViewById(R.id.tvSendUs)
        textView.setOnClickListener(this)

//        val appBarLayout = activity?.findViewById<AppBarLayout>(R.id.toolbarLayout)
//        appBarLayout?.setExpanded(true, true)

        return root
    }

    override fun onClick(v: View?) {
        findNavController().navigate(R.id.navigation_contact)
    }
}
