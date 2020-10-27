package com.shamilov.common.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf

/**
 * Created by Shamilov on 27.10.2020
 */
sealed class NavigationCommand {
    data class To(@IdRes val direction: Int, val bundle: Bundle = bundleOf()): NavigationCommand()
    object Back: NavigationCommand()
}