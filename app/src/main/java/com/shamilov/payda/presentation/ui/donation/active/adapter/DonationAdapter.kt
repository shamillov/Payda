package com.shamilov.payda.presentation.ui.donation.active.adapter

/**
 * Created by Shamilov on 20.05.2020
 */
class DonationAdapter(){}
//    private val listener: (DonationEntity) -> Unit,
//    private val donateClickListener: (Int) -> Unit,
//    private val shareListener: () -> Unit,
//    private val addToFavoriteListener: (Boolean) -> Unit
//) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    private val TAG: String = DonationAdapter::class.java.simpleName
//
//    companion object {
//        private const val TYPE_HEADER = 0
//        private const val TYPE_DONATION = 1
//    }
//
//    private val donations: MutableList<DonationEntity> = ArrayList()
//    private val searchDonation: MutableList<DonationEntity> = ArrayList()
//
//    fun setData(list: List<DonationEntity>) {
//        donations.clear()
//        searchDonation.clear()
//        donations.addAll(list)
//        searchDonation.addAll(donations)
//
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
////        return when (viewType) {
//////            TYPE_HEADER -> HeaderViewHolder(
//////                LayoutInflater.from(parent.context).inflate(R.layout.item_help_us, parent, false)
//////            )
////            TYPE_DONATION -> DonationViewHolder(
////                LayoutInflater.from(parent.context).inflate(R.layout.item_donation_active, parent, false),
////                listener,
////                donateClickListener,
////                shareListener,
////                addToFavoriteListener
////            )
////            else -> throw IllegalStateException("fail")
////        }
//        return DonationViewHolder(
//            LayoutInflater.from(parent.context).inflate(R.layout.item_donation_active, parent, false),
//            listener,
//            donateClickListener,
//            shareListener,
//            addToFavoriteListener
//        )
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        when (holder) {
////            is HeaderViewHolder -> holder.onBind()
//            is DonationViewHolder -> holder.onBind(searchDonation[position])
//        }
//    }
//
////    override fun getItemViewType(position: Int): Int {
////        return if (position == 0) TYPE_HEADER
////        else TYPE_DONATION
////    }
//
//    override fun getItemCount(): Int = searchDonation.size
//
//    fun filter(query: String) {
//        searchDonation.clear()
//        searchDonation.addAll(
//            donations.filter {
//                it.fundName.contains(query, ignoreCase = true)
//                        || it.title.contains(query, ignoreCase = true)
//                        || it.region.contains(query, ignoreCase = true)
//            }
//        )
//        notifyDataSetChanged()
//    }
//}