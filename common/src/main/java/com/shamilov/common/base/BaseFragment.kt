package com.shamilov.common.base

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.navigation.fragment.findNavController
import com.shamilov.common.utils.Event
import com.shamilov.common.utils.NavigationCommand
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

    override fun navigate(event: Event<NavigationCommand>) {
        event.getContentIfNotHandled()?.let { command ->
            when(command) {
                is NavigationCommand.To -> findNavController().navigate(command.direction, command.bundle)
                is NavigationCommand.Back -> findNavController().navigateUp()
            }
        }
    }

    protected fun <T> laziest(initializer: () -> T): Lazy<T> {
        return lazy(LazyThreadSafetyMode.NONE, initializer)
    }

}