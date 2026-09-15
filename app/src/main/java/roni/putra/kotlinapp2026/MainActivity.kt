package roni.putra.kotlinapp2026

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.v("Life Cycle","Create")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("Life Cycle","Restart")
    }

    override fun onStart() {
        super.onStart()
        Log.v("Life Cycle","Start")
    }

    override fun onResume() {
        super.onResume()
        Log.v("Life Cycle","Resume")
    }

}