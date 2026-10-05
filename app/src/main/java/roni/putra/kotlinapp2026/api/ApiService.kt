package roni.putra.kotlinapp2026.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    //Method
    @GET("api/json/v1/1/filter.php?c=Seafood")
    fun getMeal(): Call<MealModel>

}