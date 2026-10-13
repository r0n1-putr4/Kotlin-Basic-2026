package roni.putra.kotlinapp2026.map

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import roni.putra.kotlinapp2026.R
import roni.putra.kotlinapp2026.databinding.ActivityCustomeMapBinding
import kotlin.toString

class CustomeMapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityCustomeMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCustomeMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
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

        //panggil fungsi myLocation
        myLocation()

        val listCafe = listOf<CafeModel>(
            CafeModel(
                LatLng(-0.9157642304609063, 100.46057731028326),
                "Cafe Resto FISIP OMIOS",
                5.0f,
                "FISIP UNAND, Kampus Universitas Andalas, Limau Manis, Kec. Pauh, Kota Padang, Sumatera Barat 25163",
                R.drawable.img_cafe_map2
            ),
            CafeModel(
                LatLng(-0.9121587024041596, 100.45936280395524),
                "Cafe Onang",
                4.6f,
                "Jl. Universitas Andalas, Limau Manis, Kec. Pauh, Kota Padang, Sumatera Barat 25175",
                R.drawable.img_cafe_map3
            ),
            CafeModel(
                LatLng(-0.9117806449133826, 100.4575002526705),
                "The Pantry Cafe",
                4.0f,
                "Gedung fakultas ekonomi universitas Andalas, Limau Manis, Kec. Pauh, Kota Padang, Sumatera Barat 25175",
                R.drawable.img_cafe_map4
            )
        )

        // Add a marker in Sydney and move the camera
        val titikTengah = LatLng(-0.9128104984356791, 100.46119153780619)

        for (i in listCafe) {
            val marker = mMap.addMarker(
                MarkerOptions()
                    .position(i.koordinat)
            )
            //simpan marker dalam tag
            marker?.tag = i
        }

        //ketika marker di klik
        mMap.setOnMarkerClickListener { marker ->
            val dataItemCafe = marker.tag as? CafeModel
            if (dataItemCafe != null) {
                showCafe(dataItemCafe)
            }
            true
        }
        //Hidden card Ketika Map di klik
        mMap.setOnMapClickListener {
            binding.cvCafe.visibility = View.GONE
        }

        //panggil fungsi retro
        themeRetro()

        themeAubergine()

        tipeSatellite()


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(titikTengah, 13f))
    }

    fun showCafe(cafeModel: CafeModel) {
        binding.tvNamaTempat.text = cafeModel.namaTempat
        binding.tvRating.text = cafeModel.rating.toString()
        binding.rbMap.rating = cafeModel.rating
        binding.tvAlamat.text = cafeModel.alamat
        binding.ivCafe.setImageResource(cafeModel.gambar)
        binding.cvCafe.visibility = View.VISIBLE
    }

    fun tipeSatellite() {
        binding.fbTipeSatelite.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        }
    }

    fun themeRetro() {
        binding.fbRetro.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            setThemeMap(R.raw.theme_retro)
        }
    }

    fun themeAubergine() {
        binding.fbAubergine.setOnClickListener {
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            setThemeMap(R.raw.theme_aubergine)
        }
    }

    //fungsi membaca file theme dari json
    fun setThemeMap(mapThemeFile: Int) {
        mMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(
                this,
                mapThemeFile
            )
        )
    }

    //fungsi GPS
    fun myLocation() {
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
}