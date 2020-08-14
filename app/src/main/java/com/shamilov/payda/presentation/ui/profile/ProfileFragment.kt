package com.shamilov.payda.presentation.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.shamilov.payda.R
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by Shamilov on 20.05.2020
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profile, container, false)

//        val appBarLayout = activity?.findViewById<AppBarLayout>(R.id.toolbarLayout)
//        appBarLayout?.elevation = 5F

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
    }

    private fun initClickListener() {
        tvProfileAssist.setOnClickListener {
            findNavController().navigate(R.id.navigation_assistance)
        }
        tvProfileAboutApp.setOnClickListener {
            findNavController().navigate(R.id.navigation_about_app)
        }
    }
}
