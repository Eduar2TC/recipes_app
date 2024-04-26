package com.example.recipesfood.views.custom_views

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class CustomBottomMenu(context: Context) {
    private val coordinatorLayout: CoordinatorLayout
    private val bottomNavigationMenuView: BottomNavigationView

    init {
        coordinatorLayout = (context as AppCompatActivity).findViewById<CoordinatorLayout>(com.example.recipesfood.R.id.coordinator_layout) as CoordinatorLayout
        bottomNavigationMenuView = (context as AppCompatActivity).findViewById<BottomNavigationView>(
            com.example.recipesfood.R.id.bottomNavigationView) as BottomNavigationView
        setupBottomMenu()
    }

    private fun setupBottomMenu() {
        NavigationBarView.OnItemReselectedListener  {
            item ->
            when (item.itemId) {
                com.example.recipesfood.R.id.bottom_action_browse -> {
                    // Respond to navigation item 1 click
                    true
                }
                com.example.recipesfood.R.id.bottom_action_share -> {
                    // Respond to navigation item 2 click
                    true
                }
                com.example.recipesfood.R.id.bottom_action_save-> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }


}
