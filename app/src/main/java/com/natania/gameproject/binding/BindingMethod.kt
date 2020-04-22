package com.natania.gameproject.binding

import android.widget.ImageView
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

@BindingMethods(
    BindingMethod(type = ImageView::class,
        attribute = "app:srcCompat",
        method = "setImageResource")
)
class BindingMethods



