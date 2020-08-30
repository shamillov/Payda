package com.shamilov.payda.extension

import android.view.View
import androidx.appcompat.widget.Toolbar

/**
 * Created by Shamilov on 15.08.2020
 */
fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Toolbar.addBackButton(callback: (View) -> Unit) {
    setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
    setNavigationOnClickListener { callback.invoke(this) }
}