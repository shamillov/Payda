package com.shamilov.payda.utils

import android.content.Context
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants

/**
 * Created by Shamilov on 01.06.2020
 */
object GooglePaymentUtils {
    fun createGooglePay(context: Context): PaymentsClient {
        return Wallet.getPaymentsClient(
            context, Wallet.WalletOptions.Builder()
                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                .setTheme(WalletConstants.THEME_LIGHT)
                .build()
        )
    }
}