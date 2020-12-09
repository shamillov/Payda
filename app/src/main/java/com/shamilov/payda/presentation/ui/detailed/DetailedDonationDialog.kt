package com.shamilov.payda.presentation.ui.detailed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shamilov.payda.databinding.BottomSheetDetailedDonationBinding
import com.shamilov.payda.domain.model.DonationEntity

class DetailedDonationDialog : BottomSheetDialogFragment() {

    companion object {
        const val DONATION_ARGUMENTS_KEY = "DONATION_ARGUMENTS_KEY"
    }

    private var _binding: BottomSheetDetailedDonationBinding? = null
    private val binding: BottomSheetDetailedDonationBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetDetailedDonationBinding.inflate(inflater, container, false)

        arguments?.let {
            val donation: DonationEntity? = it.getParcelable(DONATION_ARGUMENTS_KEY)

            binding.tvTitle.text = donation?.title
            binding.tvDescription.text = donation?.description
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}