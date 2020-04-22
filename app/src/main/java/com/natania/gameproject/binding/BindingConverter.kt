package com.natania.gameproject.binding

import android.view.View
import androidx.databinding.BindingConversion


object ConverterCommonUtil{
    @JvmStatic fun isZero(position: Int): Boolean{
        return  position == 0
    }
}

object BindingConverterCommonUtil{
    @BindingConversion
    @JvmStatic fun booleanToVisibility(isNotVisible: Boolean):Int{
        return if (isNotVisible) View.GONE else View.VISIBLE
    }
}