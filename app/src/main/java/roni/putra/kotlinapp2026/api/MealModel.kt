package roni.putra.kotlinapp2026.api

import java.io.Serializable


data class MealModel(
    val meals: List<Meal>
) {
    data class Meal(
        val idMeal: String,
        val strMeal: String,
        val strMealThumb: String
    ) : Serializable

}
