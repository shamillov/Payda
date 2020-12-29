package com.shamilov.payda.presentation.ui.funds

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.R
import com.shamilov.payda.databinding.FragmentFundsBinding
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import moxy.ktx.moxyPresenter

/**
 * Created by Shamilov on 20.05.2020
 */
class FundsFragment : BaseFragment(R.layout.fragment_funds), FundsView {

    private var _binding: FragmentFundsBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter { FundsPresenter() }
    private val adapter by laziest { GroupAdapter<GroupieViewHolder>() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentFundsBinding.bind(view)

        initRecyclerView()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun setFunds(items: List<Group>) {
        adapter.addAll(items)
    }

    private fun initRecyclerView() {
        binding.rvFunds.adapter = adapter
        binding.rvFunds.addItemDecoration(DividerItemDecoration())
    }

    inner class DividerItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.top = resources.getDimension(R.dimen.default_margin).toInt()
            outRect.left = resources.getDimension(R.dimen.default_margin).toInt()
            outRect.right = resources.getDimension(R.dimen.default_margin).toInt()

            if (parent.getChildAdapterPosition(view) == adapter.itemCount - 1) {
                outRect.bottom = resources.getDimension(R.dimen.default_margin).toInt()
            }
        }
    }
}
