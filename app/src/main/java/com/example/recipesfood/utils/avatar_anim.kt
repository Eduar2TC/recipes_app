package com.example.recipesfood.utils

import android.content.Context
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.recipesfood.R
import com.example.recipesfood.views.MainActivity

fun showAvatarAnimation(context: Context) {
    val avatar = (context as MainActivity).findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.circle_avatar) as androidx.constraintlayout.widget.ConstraintLayout
    val animation = AnimationUtils.loadAnimation(context, R.anim.avatar_show)
    avatar.setOnClickListener {
        Toast.makeText(context, "Avatar clicked", Toast.LENGTH_SHORT).show()
        avatar.startAnimation(animation)
    }
    avatar.startAnimation(animation)
}