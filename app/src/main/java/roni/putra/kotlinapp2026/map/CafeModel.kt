package roni.putra.kotlinapp2026.map

import com.google.android.gms.maps.model.LatLng

data class CafeModel(
    val koordinat: LatLng,
    val namaTempat: String,
    val rating: Float,
    val alamat: String,
    val gambar: Int
)
