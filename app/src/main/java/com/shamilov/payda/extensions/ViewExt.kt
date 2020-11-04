package com.shamilov.payda.extensions

import android.view.View
import androidx.appcompat.widget.Toolbar
import com.shamilov.payda.R

/**
 * Created by Shamilov on 15.08.2020
 */
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Toolbar.addBackButton(callback: (View) -> Unit) {
    setNavigationIcon(R.drawable.abc_ic_ab_back_material)
    setNavigationOnClickListener { callback.invoke(this) }
}