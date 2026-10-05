package roni.putra.kotlinapp2026.api

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import roni.putra.kotlinapp2026.R

class MealActivity : AppCompatActivity() {
    private lateinit var rvMeal: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_meal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        rvMeal = findViewById(R.id.rvMeal)
    }

    override fun onStart() {
        super.onStart()
        showMeal()
    }

    fun showMeal() {
        ApiClient.apiService.getMeal().enqueue(object : Callback<MealModel> {
            override fun onResponse(call: Call<MealModel?>, response: Response<MealModel?>) {
                if (response.isSuccessful) {
                    rvMeal.adapter = MealAdapter(response.body()!!.meals)
                } else {
                    Toast.makeText(this@MealActivity, "Gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MealModel?>, t: Throwable) {
                Toast.makeText(this@MealActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}