package com.shamilov.payda.presentation.ui.fund

import android.os.Bundle
import android.view.View
import coil.load
import coil.transform.CircleCropTransformation
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.databinding.FragmentFundBinding
import com.shamilov.payda.domain.model.FundEntity
import moxy.ktx.moxyPresenter

/**
 * Created by Shamilov on 29.12.2020
 */
class FundFragment : BaseFragment(R.layout.fragment_fund), FundView {

    companion object {
        const val KEY_FUND_ID = "FUND_ID"
    }

    private var _binding: FragmentFundBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter { FundPresenter(requireArguments()[KEY_FUND_ID] as Int) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFundBinding.bind(view)

        initListeners()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun setFund(fund: FundEntity) {
//        binding.ivLogo.load(fund.logo.name) {
//            crossfade(true)
//            transformations(CircleCropTransformation())
//        }
        binding.tvName.text = fund.name
        binding.tvRegion.text = fund.region
    }
    
    private fun initListeners() {
        binding.btnDonate.setOnClickListener {  }
        binding.btnMail.setOnClickListener {  }
        binding.btnInfo.setOnClickListener {  }
    }
}