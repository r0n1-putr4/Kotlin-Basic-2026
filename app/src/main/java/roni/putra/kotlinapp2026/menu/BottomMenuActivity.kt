package roni.putra.kotlinapp2026.menu

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import roni.putra.kotlinapp2026.MainActivity
import roni.putra.kotlinapp2026.R

class BottomMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bottom_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Tandai ikon "Home" sebagai item yang sedang aktif
        bottomNav.selectedItemId = R.id.navigation_home

        bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId){
//                R.id.navigation_home -> true
                R.id.navigation_dashboard -> {
                    // Pindah ke DashboardActivity
                    startActivity(Intent(this, MainActivity::class.java))
//                    finish() // Tutup activity lama
                    true
                }
                else -> false
            }
        }
    }
}