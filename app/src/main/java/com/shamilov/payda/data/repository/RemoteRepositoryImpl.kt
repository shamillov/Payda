package com.shamilov.payda.data.repository

import com.shamilov.payda.data.mapper.DonationMapper
import com.shamilov.payda.data.model.request.PaymentRequest
import com.shamilov.payda.data.model.response.PaymentResponse
import com.shamilov.payda.data.remote.api.DonationService
import com.shamilov.payda.domain.model.DonationEntity
import com.shamilov.payda.domain.repository.RemoteRepository
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import okhttp3.MediaType.Companion.toMediaType;
import okhttp3.RequestBody.Companion.asRequestBody

/**
 * Created by Shamilov on 20.05.2020
 */
class RemoteRepositoryImpl(
    private var api: DonationService,
    private val mapper: DonationMapper
) : RemoteRepository {

    override fun getDonation(): Single<List<DonationEntity>> {
        return api.getDonation()
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

//    override fun attachFilesToFee(id: Int, files: List<File>): Single<DonationEntity> {
//        val toList = files.map {
//            MultipartBody.Part.createFormData("files", it.name, it.asRequestBody())
//        }.toList();
//
//        return api.attachFile(id, toList).map { mapper.mapDonation(it) }
//    }


}