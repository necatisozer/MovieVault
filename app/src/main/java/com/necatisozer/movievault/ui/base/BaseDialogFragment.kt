package com.necatisozer.movievault.ui.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.necatisozer.movievault.extension.getClassTag

abstract class BaseDialogFragment<B : ViewDataBinding> :
    BaseFragment<B>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NO_FRAME,
            android.R.style.Theme_Material_NoActionBar_Fullscreen
        )
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    fun show(supportFragmentManager: FragmentManager) {
        super.show(supportFragmentManager, getClassTag())
    }
}