package com.natania.gameproject.binding

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.databinding.BindingAdapter

import com.natania.gameproject.R

object BindingAdapter {
    @androidx.databinding.BindingAdapter("app:popularityIcon")
    @JvmStatic
    fun popularityIcon(imageView: ImageView, popularity: ObservableViewModel.LikesNumber) {
        val color = getAssociateColor(popularity, imageView.context)
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color))
        imageView.setImageDrawable(getDrawablePopularity(popularity, imageView.context))

    }

    @androidx.databinding.BindingAdapter("app:worstyIcon")
    @JvmStatic
    fun worstyIcon(imageView: ImageView, worsty: ObservableViewModel.UnlikesNumber) {
        val color = getSecondAssociateColor(worsty, imageView.context)
        ImageViewCompat.setImageTintList(imageView, ColorStateList.valueOf(color))
        imageView.setImageDrawable(getDrawableWorsty(worsty, imageView.context))

    }
    @BindingAdapter("app:hideIfZero")
    @JvmStatic fun hideIfZero(view: View, position: Int) {
        view.visibility = if (position == 0) View.GONE
        else View.VISIBLE
    }

    private fun getDrawableWorsty(
        worsty: ObservableViewModel.UnlikesNumber,
        context: Context
    ): Drawable? {
        return when (worsty) {
            ObservableViewModel.UnlikesNumber.NORMAL ->
                ContextCompat.getDrawable(context, R.drawable.ic_unlike)
            ObservableViewModel.UnlikesNumber.BAD -> {
                ContextCompat.getDrawable(context, R.drawable.ic_unlike)
            }
            ObservableViewModel.UnlikesNumber.WORST -> {
                ContextCompat.getDrawable(context, R.drawable.ic_unlike)
            }
        }
    }

    private fun getDrawablePopularity(
        popularity: ObservableViewModel.LikesNumber,
        context: Context
    ): Drawable? {
        return when (popularity) {
            ObservableViewModel.LikesNumber.NORMAL -> {
                ContextCompat.getDrawable(context, R.drawable.ic_like)
            }
            ObservableViewModel.LikesNumber.POPULAR -> {
                ContextCompat.getDrawable(context, R.drawable.ic_like)
            }
            ObservableViewModel.LikesNumber.STAR -> {
                ContextCompat.getDrawable(context, R.drawable.ic_like)
            }
        }
    }

    private fun getSecondAssociateColor(
        worsty: ObservableViewModel.UnlikesNumber,
        context: Context
    ): Int {
        return when (worsty) {
            ObservableViewModel.UnlikesNumber.WORST -> ContextCompat.getColor(
                context,
                R.color.black
            )
            ObservableViewModel.UnlikesNumber.BAD -> ContextCompat.getColor(
                context,
                R.color.black
            )
            ObservableViewModel.UnlikesNumber.NORMAL -> ContextCompat.getColor(
                context,
                R.color.black
            )

        }
    }

    private fun getAssociateColor(
        popularity: ObservableViewModel.LikesNumber,
        context: Context
    ): Int {
        return when (popularity) {
            ObservableViewModel.LikesNumber.STAR -> ContextCompat.getColor(
                context,
                R.color.black
            )
            ObservableViewModel.LikesNumber.POPULAR -> ContextCompat.getColor(
                context,
                R.color.black
            )
            ObservableViewModel.LikesNumber.NORMAL -> ContextCompat.getColor(
                context,
                R.color.black
            )

        }
    }
}