package com.shamilov.payda.presentation.ui.donation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shamilov.payda.R
import kotlinx.android.synthetic.main.bottom_sheet_about_donation.*

class AboutDialog() : BottomSheetDialogFragment() {

    companion object {
        private const val KEY = "BS"
        fun newInstance(title: String): AboutDialog {
            val args = Bundle()
            args.putString(KEY, title)

            return AboutDialog().apply {
                arguments = args
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_about_donation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleTvBs.text = arguments?.getString(KEY)
    }

}