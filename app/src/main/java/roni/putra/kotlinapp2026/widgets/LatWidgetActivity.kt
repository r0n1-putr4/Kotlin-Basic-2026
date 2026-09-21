package roni.putra.kotlinapp2026.widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import roni.putra.kotlinapp2026.R
import kotlin.collections.plusAssign
import kotlin.text.get
import kotlin.toString

class LatWidgetActivity : AppCompatActivity() {
    private lateinit var pelanggan: Spinner
    private lateinit var btnSimpan: Button
    private lateinit var chkPokat: CheckBox
    private lateinit var etjlhPokat: EditText

    private lateinit var etJlhMangga: TextView
    private lateinit var chkMangga: CheckBox

    private lateinit var tvhasil: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lat_widget)

        //inisialisasi
        pelanggan = findViewById(R.id.spPelanggan)
        btnSimpan = findViewById(R.id.btnSimpan)
        chkPokat = findViewById(R.id.chkPokat)
        etjlhPokat = findViewById(R.id.etjlhPokat)
        chkMangga = findViewById(R.id.chkMangga)
        etJlhMangga = findViewById(R.id.etJlhMangga)
        tvhasil = findViewById(R.id.tvhasil)
    }

    override fun onStart() {
        super.onStart()
        val checkboxes = arrayOf(
            chkPokat,
            chkMangga
        )

        val editTexts = arrayOf(
            etjlhPokat,
            etJlhMangga
        )

        for (i in editTexts.indices) {
            editTexts[i].addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    checkboxes[i].isChecked = !s.isNullOrEmpty()
                }
            })
        }
        val harga = intArrayOf(
            10000,
            15000,
        )

        var total = 0

        //Paling fungsi proses
        btnSimpan.setOnClickListener {
            var pilMenu = ""
            val pelanggan = pelanggan.selectedItem

            val dis = when (pelanggan) {
                "vip" -> 0.1
                "member" -> 0.05
                else -> 0.0
            }

            for (i in harga.indices) {
                if (checkboxes[i].isChecked) {
                    val jumlah = editTexts[i]
                        .text
                        .toString()
                        .toIntOrNull() ?: 1

                    pilMenu = pilMenu + "\n" + checkboxes[i].text + " x " + jumlah
                    total += harga[i] * jumlah
                }
            }

            val potongan = if (total >= 100_000) 1000 else 0
            val totalDis = total * dis

            val totalBayar = total - totalDis - potongan

            tvhasil.text =
                "$pilMenu\nDiskon : Rp $totalDis\nPotongan : Rp $potongan\nTotal: Rp $totalBayar\n"
        }

    }


}