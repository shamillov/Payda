package com.shamilov.payda.presentation.ui.donation.active

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.shamilov.common.base.BaseFragment
import com.shamilov.payda.BuildConfig
import com.shamilov.payda.R
import com.shamilov.payda.data.local.datastore.SettingsDatastore
import com.shamilov.payda.databinding.FragmentActiveBinding
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.extensions.gone
import com.shamilov.payda.extensions.visible
import com.shamilov.payda.presentation.ui.donation.active.viewholder.ShimmerViewHolder
import com.shamilov.payda.utils.HostSelectionInterceptor
import com.xwray.groupie.Group
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import jp.wasabeef.recyclerview.animators.FadeInRightAnimator
import kotlinx.android.synthetic.main.fragment_active.*
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.inject
import ru.yandex.money.android.sdk.Checkout
import ru.yandex.money.android.sdk.PaymentParameters
import ru.yandex.money.android.sdk.UiParameters

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationActiveFragment : BaseFragment(R.layout.fragment_active), DonationActiveView {

    private val TAG = DonationActiveFragment::class.java.simpleName

    companion object {
        const val TOKEN_REQUEST_CODE = 1
    }

    private var _binding: FragmentActiveBinding? = null
    private val binding: FragmentActiveBinding get() = _binding!!

    private val presenter by moxyPresenter { DonationActivePresenter() }
    private val donationAdapter by lazy { GroupAdapter<GroupieViewHolder>() }

    //TODO: Временное решение
    private val dataStore: SettingsDatastore by inject()
    private val hostSelectionInterceptor: HostSelectionInterceptor by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentActiveBinding.bind(view)

        setHost()

        initRecyclerView()
        initViews()
        initListeners()
        presenter.loadData(isNetworkAvailable())
    }

    private fun initRecyclerView() {
        binding.recyclerViewActive.apply {
            setHasFixedSize(true)
            adapter = donationAdapter
        }

        if (donationAdapter.itemCount == 0) {
            recyclerViewActive.itemAnimator = FadeInRightAnimator()
            recyclerViewActive.itemAnimator?.apply {
                addDuration = 300
                removeDuration = 300
                moveDuration = 300
                changeDuration = 300
            }

            donationAdapter.apply {
                add(ShimmerViewHolder())
                add(ShimmerViewHolder())
                add(ShimmerViewHolder())
            }
        }
    }

    private fun initViews() {
        binding.swipeRefreshDonationActive.setColorSchemeColors(
            ContextCompat.getColor(
                requireContext(),
                R.color.colorPrimary
            )
        )
    }

    override fun showLoading(loading: Boolean) {
        if (loading) {
            binding.recyclerViewActive.gone()
            binding.progressBarActive.visible()
        } else {
            binding.recyclerViewActive.visible()
            binding.progressBarActive.gone()
        }
    }

    override fun showSwipeLoading(loading: Boolean) {
        binding.swipeRefreshDonationActive.isRefreshing = loading
    }

    override fun showNetworkError(showError: Boolean) {
        if (showError) {
            binding.tvNetworkErrorActive.visible()
            binding.recyclerViewActive.gone()
        } else {
            binding.tvNetworkErrorActive.gone()
            binding.recyclerViewActive.visible()
        }
    }

    override fun onSuccess(data: List<Group>) {
        donationAdapter.update(data)
    }

    override fun onFailure(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    override fun onUpdate(data: List<Group>) {
        donationAdapter.update(data)
    }

    override fun openDonation(donation: DonationEntity) {
        initBottomSheet(donation)
    }

    override fun showEmptyMessage(show: Boolean) {
        if (show)
            binding.tvEmptyMessage.visible()
        else
            binding.tvEmptyMessage.gone()
    }

    override fun donate(parameter: PaymentParameters, uiParameters: UiParameters) {
        val intent = Checkout.createTokenizeIntent(requireContext(), parameter, uiParameters = uiParameters)
        startActivityForResult(intent, TOKEN_REQUEST_CODE)
    }

    override fun shareDonation() {
        val share = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Ссылка на приложение")
        }
        startActivity(Intent.createChooser(share, null))
    }

    private fun initBottomSheet(donation: DonationEntity) {
        val dialog = BottomSheetDialog(requireContext())
        val bottomSheet: View =
            layoutInflater.inflate(R.layout.bottom_sheet_about_donation, view as ViewGroup, false)

        bottomSheet.apply {
            findViewById<TextView>(R.id.titleTvBs).text = donation.title
            findViewById<TextView>(R.id.descriptionTvBs).text = donation.description
        }

        dialog.setContentView(bottomSheet)
        dialog.show()
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TOKEN_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> data?.let {
                    val token = Checkout.createTokenizationResult(it)
                    MaterialAlertDialogBuilder(requireContext())
                        .setMessage(token.toString())
                        .create()
                        .show()
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initListeners() {
        binding.swipeRefreshDonationActive.setOnRefreshListener { presenter.refreshData() }
    }

    private fun setHost() {
//        lifecycleScope.launch {
//            if(dataStore.getHost.first().isNotEmpty()) {
//                hostSelectionInterceptor.setHost(dataStore.getHost.first())
//            } else {
//                hostSelectionInterceptor.setHost(BuildConfig.PAYDA_SERVICE_HOST)
//            }
//        }
        hostSelectionInterceptor.setHost(BuildConfig.PAYDA_SERVICE_HOST)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    inner class DividerItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = 16
            }
        }
    }

}
