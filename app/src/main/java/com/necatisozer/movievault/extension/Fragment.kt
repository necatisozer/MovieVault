package com.necatisozer.movievault.extension

import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    activity?.toast(message, duration)

inline fun Fragment.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder) =
    activity?.alertDialog(body)