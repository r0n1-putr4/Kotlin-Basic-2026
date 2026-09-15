package roni.putra.kotlinapp2026.widgets

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.switchmaterial.SwitchMaterial
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import roni.putra.kotlinapp2026.R

class SpinnerDateTimeActivity : AppCompatActivity() {

    private lateinit var spinJurusan: Spinner
    private lateinit var datePicker: DatePickerDialog.OnDateSetListener
    private lateinit var timePicker: TimePickerDialog.OnTimeSetListener
    private lateinit var tglMasuk: TextView
    private lateinit var jam: TextView
    private lateinit var swStatus: SwitchMaterial
    private lateinit var btnProses: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner_date_time)

        spinJurusan = findViewById(R.id.spinJurusan)
        tglMasuk = findViewById(R.id.tglMasuk)
        jam = findViewById(R.id.jam)
        swStatus = findViewById(R.id.swAktif)
        btnProses = findViewById(R.id.btnProses)

        var status = "Tidak Aktif"
        swStatus.setOnClickListener {
            status = if (swStatus.isChecked) "Aktif" else "Tidak Aktif"
            //Toast.makeText(applicationContext,status,Toast.LENGTH_SHORT).show()
        }

        btnProses.setOnClickListener {
            Toast.makeText(applicationContext, status, Toast.LENGTH_SHORT).show()
        }

        swStatus.setOnCheckedChangeListener { _, isChecked  ->
            if (isChecked) {
                // Switch ON
                println("Status Aktif")
            } else {
                // Switch OFF
                println("Status Tidak Aktif")
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val listJurusan = arrayOf(
            "Teknologi Informasi",
            "Teknik Sipil", "Mesin", "Akuntansi", "Administrasi Bisnis"
        )
        val adapterSpin = ArrayAdapter(this,
            R.layout.spin_style, listJurusan)
        spinJurusan.adapter = adapterSpin

        tanggal()
        jam()

    }

    private fun tanggal() {
        val myCalender = Calendar.getInstance()
        datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalender[Calendar.YEAR] = year
            myCalender[Calendar.MONTH] = month
            myCalender[Calendar.DAY_OF_MONTH] = dayOfMonth

            val myFormat = "dd-MMMM-yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)

            tglMasuk.text = sdf.format(myCalender.time)

        }

        tglMasuk.setOnClickListener {
            DatePickerDialog(
                this, datePicker,
                myCalender[Calendar.YEAR],
                myCalender[Calendar.MONTH],
                myCalender[Calendar.DAY_OF_MONTH]

            ).show()
        }
    }

    private fun jam() {
        val currentTime = Calendar.getInstance()
        timePicker = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            currentTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
            currentTime.set(Calendar.MINUTE, minute)
            val myStringInfo = SimpleDateFormat("HH:mm").format(currentTime.time)
            jam.text = myStringInfo
        }

        jam.setOnClickListener {
            TimePickerDialog(
                this, timePicker,
                currentTime.get(Calendar.HOUR_OF_DAY),
                currentTime.get(Calendar.MINUTE),
                true
            ).show()
        }
    }
}