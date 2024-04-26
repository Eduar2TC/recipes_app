package com.example.recipesfood.data

import android.content.Context
import android.util.Log
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.JsonObjectRequest
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

//get data from api
class DataApi {
    lateinit var requestQueue: RequestQueue
    //feching data from api
    var recipes = arrayListOf<Meal>()
    suspend fun fetchData(context: Context): ArrayList<Meal> {
        return suspendCoroutine { continuation ->
            val url = "https://www.themealdb.com/api/json/v1/1/random.php"
            val appnetwork = BasicNetwork(HurlStack())
            val appcache = DiskBasedCache(context.cacheDir, 1024 * 1024) // 1MB cap
            requestQueue = RequestQueue(appcache, appnetwork).apply {
                start()
            }

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    val jsonArray = response.getJSONArray("meals")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val meal = Meal(
                            jsonObject.getString("idMeal"),
                            jsonObject.getString("strMeal"),
                            jsonObject.getString("strDrinkAlternate"),
                            jsonObject.getString("strCategory"),
                            jsonObject.getString("strArea"),
                            jsonObject.getString("strInstructions"),
                            jsonObject.getString("strMealThumb"),
                            jsonObject.getString("strTags"),
                            jsonObject.getString("strYoutube"),
                            jsonObject.getString("strIngredient1"),
                            jsonObject.getString("strIngredient2"),
                            jsonObject.getString("strIngredient3"),
                            jsonObject.getString("strIngredient4"),
                            jsonObject.getString("strIngredient5"),
                            jsonObject.getString("strIngredient6"),
                            jsonObject.getString("strIngredient7"),
                            jsonObject.getString("strIngredient8"),
                            jsonObject.getString("strIngredient9"),
                            jsonObject.getString("strIngredient10"),
                            jsonObject.getString("strIngredient11"),
                            jsonObject.getString("strIngredient12"),
                            jsonObject.getString("strIngredient13"),
                            jsonObject.getString("strIngredient14"),
                            jsonObject.getString("strIngredient15"),
                            jsonObject.getString("strIngredient16"),
                            jsonObject.getString("strIngredient17"),
                            jsonObject.getString("strIngredient18"),
                            jsonObject.getString("strIngredient19"),
                            jsonObject.getString("strIngredient20"),
                            jsonObject.getString("strMeasure1"),
                            jsonObject.getString("strMeasure2"),
                            jsonObject.getString("strMeasure3"),
                            jsonObject.getString("strMeasure4"),
                            jsonObject.getString("strMeasure5"),
                            jsonObject.getString("strMeasure6"),
                            jsonObject.getString("strMeasure7"),
                            jsonObject.getString("strMeasure8"),
                            jsonObject.getString("strMeasure9"),
                            jsonObject.getString("strMeasure10"),
                            jsonObject.getString("strMeasure11"),
                            jsonObject.getString("strMeasure12"),
                            jsonObject.getString("strMeasure13"),
                            jsonObject.getString("strMeasure14"),
                            jsonObject.getString("strMeasure15"),
                            jsonObject.getString("strMeasure16"),
                            jsonObject.getString("strMeasure17"),
                            jsonObject.getString("strMeasure18"),
                            jsonObject.getString("strMeasure19"),
                            jsonObject.getString("strMeasure20"),
                            jsonObject.getString("strSource"),
                            //jsonObject.getString("srtImageSource"),
                            jsonObject.getString("strCreativeCommonsConfirmed"),
                            jsonObject.getString("dateModified"),
                        )
                        recipes.add(meal)
                    }
                    //print("---> recipes: $recipes")
                    continuation.resume(recipes)
                },
                { error ->
                    Log.d("Error:", error.toString())
                    continuation.resumeWithException(error)
                }
            ).setRetryPolicy(
                DefaultRetryPolicy(
                    DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
                )
            )

            requestQueue.add(jsonObjectRequest)
        }
    }

}