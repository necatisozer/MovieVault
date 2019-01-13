package com.necatisozer.movievault.extension

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun DialogFragment.showIfNonExistent(manager: FragmentManager, tag: String) {
    if (manager.findFragmentByTag(tag) == null)
        this.show(manager, tag)
}

fun DialogFragment.showNowIfNonExistent(manager: FragmentManager, tag: String) {
    if (manager.findFragmentByTag(tag) == null)
        this.showNow(manager, tag)
}