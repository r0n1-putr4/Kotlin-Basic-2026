package roni.putra.kotlinapp2026

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.dhaval2404.imagepicker.ImagePicker
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    private lateinit var imgFoto: ImageView
    private lateinit var btnPilihFoto: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.v("Life Cycle", "Create")
        imgFoto = findViewById(R.id.imgFoto)
        btnPilihFoto = findViewById(R.id.btnPilihFoto)

        imgFoto.setImageResource(intent.getIntExtra("gambar",0))
        btnPilihFoto.setOnClickListener {
            ImagePicker.with(this)
                .crop()
                .compress(1024)
                .start()

        }

       val tvRating = findViewById<TextView>(R.id.tvRating)
        tvRating.text = "Rating : ${intent.getDoubleExtra("rating",0.0)}"
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data
            imgFoto.setImageURI(uri)
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("Life Cycle", "Restart")
    }

    override fun onStart() {
        super.onStart()
        Log.v("Life Cycle", "Start")
    }

    override fun onResume() {
        super.onResume()
        Log.v("Life Cycle", "Resume")
    }

}