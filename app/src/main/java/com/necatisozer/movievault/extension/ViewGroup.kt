package com.necatisozer.movievault.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

operator fun ViewGroup.plusAssign(child: View) = addView(child)
operator fun ViewGroup.minusAssign(child: View) = removeView(child)

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)