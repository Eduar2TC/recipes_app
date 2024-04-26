package com.example.recipesfood

import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recipesfood.data.Meal
import com.google.android.material.chip.Chip

class MealAdapter(private val mealList: ArrayList<Meal> ) : RecyclerView.Adapter<MealAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(mealList[position])

    }
    override fun getItemCount(): Int {

        return mealList.size
    }

    fun updateData(data: ArrayList<Meal>){
        mealList.clear()
        mealList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindItems(meal: Meal) {
            val mealName = itemView.findViewById<TextView>(R.id.title_card) as TextView
            val mealThumbImage = itemView.findViewById<ImageView>(R.id.image_card) as ImageView
            val instructions = itemView.findViewById<TextView>(R.id.instructions_card) as TextView
            val category = itemView.findViewById<Chip>(R.id.chip_category) as Chip
            val area = itemView.findViewById<Chip>(R.id.chip_area) as Chip
            val circularProgressDrawable = CircularProgressDrawable(itemView.context)
                                        circularProgressDrawable.strokeWidth = 10f
                                        circularProgressDrawable.centerRadius = 35f
                                        circularProgressDrawable.setColorSchemeColors(androidx.appcompat.R.color.primary_material_light)
                                        circularProgressDrawable.start()

            mealName.text = meal.strMeal
            instructions.text = meal.strInstructions
            instructions.movementMethod = ScrollingMovementMethod()
            instructions.setTextIsSelectable(true)
            Glide.with(itemView.context)
                .load(sanitizeUrlImage(meal.strMealThumb))
                .apply(RequestOptions()
                    .placeholder(circularProgressDrawable)
                    .error(R.drawable.ic_launcher_background))
                .into(mealThumbImage)
            category.text = meal.strCategory
            area.text = meal.strArea
        }
        private fun sanitizeUrlImage(url: String): String {
            return url.replace("\\", "")
        }
    }

}