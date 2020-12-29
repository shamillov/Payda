package com.shamilov.payda.data.repository

import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.model.request.PaymentRequest
import com.shamilov.payda.data.model.response.PaymentResponse
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.model.FundEntity
import com.shamilov.payda.domain.repository.RemoteRepository
import io.reactivex.Single

/**
 * Created by Shamilov on 20.05.2020
 */
class RemoteRepositoryImpl(
    private var api: DonationService,
    private val mapper: DonationMapper
) : RemoteRepository {

    override fun getDonation(): Single<List<DonationEntity>> {
        return api.getDonations()
            .map { mapper.mapDonationList(it) }
    }

    override fun payment(
        id: Int,
        amount: String,
        currency: String,
        paymentToken: String
    ): Single<PaymentResponse> {
        return api.payment(id, PaymentRequest(amount, currency, paymentToken))
    }

    override fun getFunds(): Single<List<FundEntity>> {
        return api.getFunds().map { mapper.mapFundsList(it) }
    }

    override fun getFundById(id: Int): Single<FundEntity> {
        return api.getFundById(id).map { mapper.mapFund(it) }
    }

//    override fun attachFilesToFee(id: Int, files: List<File>): Single<DonationEntity> {
//        val toList = files.map {
//            MultipartBody.Part.createFormData("files", it.name, it.asRequestBody())
//        }.toList();
//
//        return api.attachFile(id, toList).map { mapper.mapDonation(it) }
//    }


}