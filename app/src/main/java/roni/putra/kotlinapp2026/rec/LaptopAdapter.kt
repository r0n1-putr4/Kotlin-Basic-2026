package roni.putra.kotlinapp2026.rec

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import roni.putra.kotlinapp2026.R

class LaptopAdapter(
    private val laptopList: List<LaptopModel>,
    private val listener: OnAdapterListener
) : RecyclerView.Adapter<LaptopAdapter.ViewProduk>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewProduk {
        return ViewProduk(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.produk_style,
                    parent, false)
        )
    }

    override fun onBindViewHolder(holder: LaptopAdapter.ViewProduk, position: Int) {
        val laptop = laptopList[position]
        holder.imgProduk.setImageResource(laptop.gambar)
        holder.tvNamaProduk.text = laptop.namaProduk
        holder.tvHarga.text = laptop.harga
        holder.tvRating.rating = laptop.rating.toFloat()
        holder.tvLokasi.text = laptop.lokasi
    }

    override fun getItemCount(): Int = laptopList.size

    class ViewProduk(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduk = view.findViewById<ImageView>(R.id.imgProduk)
        val tvNamaProduk = view.findViewById<TextView>(R.id.tvNamaProduk)
        val tvHarga = view.findViewById<TextView>(R.id.tvHarga)
        val tvRating = view.findViewById<RatingBar>(R.id.rating)
        val tvLokasi = view.findViewById<TextView>(R.id.tvLokasi)

    }

    interface OnAdapterListener {
        fun onClick(laptopModel: LaptopModel)
    }


}