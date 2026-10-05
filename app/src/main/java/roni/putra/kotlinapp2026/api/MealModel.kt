package roni.putra.kotlinapp2026.api

data class MealModel(
    val meals: List<Meal>
) {
    data class Meal(
        val idMeal: String,
        val strMeal: String,
        val strMealThumb: String
    )

}
