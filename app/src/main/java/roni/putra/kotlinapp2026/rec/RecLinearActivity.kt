package roni.putra.kotlinapp2026.rec

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import roni.putra.kotlinapp2026.R

class RecLinearActivity : AppCompatActivity() {
    private lateinit var recLaptop: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rec_linear)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recLaptop = findViewById(R.id.recLaptop)
    }

    override fun onStart() {
        super.onStart()
        showLaptop()
    }

    private fun showLaptop() {
        val laptopList = listOf<LaptopModel>(
            LaptopModel(
                R.drawable.img,
                "Lenovo Thinkpad",
                "Rp. 50.000",
                4.5,
                "Jakarta"
            ),
            LaptopModel(
                R.drawable.laptop_1,
                "Lenovo Thinkpad",
                "Rp. 50.000",
                4.5,
                "Jakarta"
            )
        )
        recLaptop.adapter = LaptopAdapter(
            laptopList,
            object : LaptopAdapter.OnAdapterListener {
                override fun onClick(laptopModel: LaptopModel) {
                    val bundle = Bundle()
                    bundle.putDouble("rating", laptopModel.rating)
                }
            })
    }
}