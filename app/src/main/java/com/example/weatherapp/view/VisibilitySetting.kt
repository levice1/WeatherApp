package com.example.weatherapp.view

import android.animation.ValueAnimator
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.databinding.ActivityMainBinding


open class VisibilitySetting(private val binding: ActivityMainBinding) {
    private val visible = View.VISIBLE
    private val invisible = View.INVISIBLE

    // спрятать кнопку, показать прогресс бар, убрать падинги
    fun setVisibleAfterGetWeather() {
        binding.inputLayout.animatePadding(60, 40, 60, 40)
        binding.scrollView.animatePadding(0, 0, 0, 0, 300)
        binding.btnFindCity.visibility = visible
        binding.progressBar.visibility = invisible
    }

    // спрятать прогресс бар и показать кнопку
    fun setInvisibleAfterPressBtn() {
        binding.btnFindCity.visibility = invisible
        binding.progressBar.visibility = visible
    }

    fun setVisibleAfterGetError() {
        binding.btnFindCity.visibility = visible
        binding.progressBar.visibility = invisible
    }

    // функция добавления анимации изменения падингов
    fun View.animatePadding(
        newPaddingStart: Int = paddingLeft,
        newPaddingTop: Int = paddingTop,
        newPaddingEnd: Int = paddingRight,
        newPaddingBottom: Int = paddingBottom,
        duration: Long = 600L
    ) {
        val start = Rect(paddingLeft, paddingTop, paddingRight, paddingBottom)
        val end = Rect(newPaddingStart, newPaddingTop, newPaddingEnd, newPaddingBottom)

        val anim = ValueAnimator.ofFloat(0f, 1f)
        anim.addUpdateListener { valueAnimator ->
            val fraction = valueAnimator.animatedFraction
            layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
                setPadding(
                    lerp(start.left, end.left, fraction),
                    lerp(start.top, end.top, fraction),
                    lerp(start.right, end.right, fraction),
                    lerp(start.bottom, end.bottom, fraction)
                )
            }
        }
        anim.duration = duration
        anim.start()
    }

    private fun lerp(startValue: Int, endValue: Int, fraction: Float): Int {
        return (startValue + fraction * (endValue - startValue)).toInt()
    }
}