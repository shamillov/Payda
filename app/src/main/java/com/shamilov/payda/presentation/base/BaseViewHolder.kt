package com.shamilov.payda.presentation.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Shamilov on 12.09.2020
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer{

    protected val context: Context
        get() = itemView.context

    override val containerView: View?
        get() = itemView
}