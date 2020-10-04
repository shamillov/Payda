package com.shamilov.payda.utils

/**
 * Created by Shamilov on 20.05.2020
 */
object Const {
    const val BASE_URL: String = "http://paydatest-env.eba-rjmpfuvm.us-east-2.elasticbeanstalk.com/"
    const val ENDPOINT_DONATION_ACTIVE: String = "api/fees"
    const val ENDPOINT_DONATION_COMPLETED: String = ""
    const val ENDPOINT_FUNDS: String = "api/funds"
    const val ENDPOINT_PAYMENT: String = "api/fees/{id}/donate/init"
}