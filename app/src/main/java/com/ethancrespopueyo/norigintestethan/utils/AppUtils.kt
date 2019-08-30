package com.ethancrespopueyo.norigintestethan.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

//less code for inflateViews
fun inflate(context: Context, viewId: Int, parent: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(viewId, parent, attachToRoot)
}