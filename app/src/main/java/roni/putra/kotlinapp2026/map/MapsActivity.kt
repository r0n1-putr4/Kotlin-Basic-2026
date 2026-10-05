package roni.putra.kotlinapp2026.map

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import roni.putra.kotlinapp2026.R
import roni.putra.kotlinapp2026.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var infoCard: CardView
    private lateinit var tvNama: TextView
    private lateinit var tvAlamat: TextView
    private lateinit var tvRating: TextView
    private lateinit var tvHarga: TextView
    private lateinit var imgRumah: ImageView
    private lateinit var btnDetail: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        infoCard = findViewById(R.id.infoCard)
        tvNama = findViewById(R.id.tvNama)
        tvAlamat = findViewById(R.id.tvAlamat)
        tvRating = findViewById(R.id.tvRating)
        tvHarga = findViewById(R.id.tvHarga)

        imgRumah = findViewById(R.id.imgRumah)
        btnDetail = findViewById(R.id.btnDetail)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        myLocation()

        val listLatLng = listOf(
            ModelLatLng(
                -0.93742627,
                100.3603608,
                "Basko",
                "Mall terbesar di Kota Padang",
                R.drawable.ic_hostpital
            ),
            ModelLatLng(
                -0.9019383839220829,
                100.3508682099663,
                "Gubernur",
                "Mall terbesar di Kota Padang",
                R.drawable.ic_hostpital
            )
        )
        val padang = LatLng(-0.93742627, 100.3603608)


        listLatLng.forEach {
            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(it.Lat, it.Lng))
                    .title(it.Title).snippet(it.Snippet)

                    .icon(BitmapDescriptorFactory.fromResource(it.icon))
            )

            mMap.setOnMarkerClickListener { marker ->
                tampilkanInfo(marker)
                true  // supaya map tidak auto zoom
            }
        }
        mMap.setOnMapClickListener {

            infoCard.visibility = View.GONE
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(padang, 12f))
    }

    private fun tampilkanInfo(marker: Marker) {

        tvNama.text = marker.title

        tvAlamat.text = marker.title

        tvRating.text =
            "⭐ 4.3"

        tvHarga.text =
            "Rp. 300.000"

        infoCard.visibility = View.VISIBLE
    }

    private fun myLocation() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String?>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {

            if (grantResults.isNotEmpty() &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {

                // Setelah user menekan IZINKAN
                myLocation()
            }
        }
    }
}