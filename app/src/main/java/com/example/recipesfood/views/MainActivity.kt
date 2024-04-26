package com.example.recipesfood.views

import CustomPopup
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipesfood.MealAdapter
import com.example.recipesfood.R
import com.example.recipesfood.data.DataApi
import com.example.recipesfood.utils.showAvatarAnimation
import com.example.recipesfood.views.custom_views.CustomBottomMenu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MealAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val customPopup = CustomPopup(this)
        val menuButton: Button = findViewById<Button>(R.id.menu_button) as Button
        menuButton.setOnClickListener {
            customPopup.show(it)
        }
        recyclerView = findViewById(R.id.list_recipes)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //init adapter with empty list
        adapter = MealAdapter(arrayListOf())
        recyclerView.adapter = adapter
        //fetch data from api
        fetchData()
        CustomBottomMenu(this)
        showAvatarAnimation(this)
    }
    private fun fetchData() {
        val dataApi = DataApi()
        GlobalScope.launch(Dispatchers.Main) {
            repeat(10){
                val data = dataApi.fetchData(this@MainActivity)
                //print(data)
                //update adapter with data
                if( data.isNotEmpty() ){
                    adapter.updateData(data)
                }
            }
        }
    }

}