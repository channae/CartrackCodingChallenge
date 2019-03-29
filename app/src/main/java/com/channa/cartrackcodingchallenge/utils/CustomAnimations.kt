package com.channa.cartrackcodingchallenge.utils

import android.content.Context
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.channa.cartrackcodingchallenge.R

class CustomAnimations {

    companion object {

        fun shake(context: Context, view: View) {
            var shake: Animation = AnimationUtils.loadAnimation(context, R.anim.animation_shake)
            view.startAnimation(shake)
        }
    }
}