package roni.putra.kotlinapp2026.api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import roni.putra.kotlinapp2026.R
import roni.putra.kotlinapp2026.rec.LaptopAdapter.ViewProduk

class MealAdapter(
    private val meals: List<MealModel.Meal>
) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealAdapter.MealViewHolder {
        return MealViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.neal_layout,
                    parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: MealAdapter.MealViewHolder, position: Int) {
        val meal = meals[position]
        Picasso.get().load(meal.strMealThumb).into(holder.ivProduk)
        holder.tvNamaMakanan.text = meal.strMeal
    }

    override fun getItemCount() = meals.size

    class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProduk = view.findViewById<ImageView>(R.id.ivProduk)
        val tvNamaMakanan = view.findViewById<TextView>(R.id.tvNamaMakanan)

    }
}