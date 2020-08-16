package com.shamilov.payda.presentation.base

import android.widget.Toast
import androidx.annotation.StringRes
import moxy.MvpAppCompatFragment

/**
 * Created by Shamilov on 12.08.2020
 */
abstract class BaseFragment(layoutId: Int) : MvpAppCompatFragment(layoutId), BaseView {

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    override fun showMessage(@StringRes id: Int) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
    }
}