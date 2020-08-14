package com.shamilov.payda.presentation.base

import android.widget.Toast
import moxy.MvpAppCompatFragment

/**
 * Created by Shamilov on 12.08.2020
 */
abstract class BaseFragment(layoutId: Int) : MvpAppCompatFragment(layoutId), BaseView {

    override fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}