package roni.putra.kotlinapp2026.widgets

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import roni.putra.kotlinapp2026.R


class CheckBoxRadioActivity : AppCompatActivity() {
    private lateinit var rgAgama: RadioGroup
    private lateinit var btnSimpan: Button
    private lateinit var chkBola: CheckBox
    private lateinit var chkRenang: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_box_radio)

        rgAgama = findViewById(R.id.rgAgama)
        btnSimpan = findViewById(R.id.btnSimpan)
        chkBola = findViewById(R.id.chkBola)
        chkRenang = findViewById(R.id.chkRenang)


        btnSimpan.setOnClickListener {
            if (rgAgama.checkedRadioButtonId != -1) {
                val agamaPil = findViewById<RadioButton>(rgAgama.checkedRadioButtonId)
                val checkbox = arrayOf(chkBola, chkRenang)
                val pilihan = checkbox.filter { it.isChecked }.map { it.text.toString() }
                Toast.makeText(
                    this,
                    "${agamaPil.text}\n${pilihan.joinToString(", ")}",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(this, "Agama Wajib di Pilih", Toast.LENGTH_SHORT).show()
            }
        }

    }
}