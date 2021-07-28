package com.alnite.sportskuy.fragments

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import com.alnite.sportskuy.BerandaActivity
import com.alnite.sportskuy.R
import com.alnite.sportskuy.SearchresActivity
import kotlinx.android.synthetic.main.activity_book.*
import java.util.*

@Suppress("DEPRECATION")
class BookActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        supportActionBar?.hide()
        window.decorView.setOnSystemUiVisibilityChangeListener(object :
            View.OnSystemUiVisibilityChangeListener {
            fun OnSystemUiVisibilityChange(visibility: Int) {
                if (visibility == 0) window.decorView.setSystemUiVisibility(hideSystemBar())
            }

            override fun onSystemUiVisibilityChange(visibility: Int) {
                if (visibility == 0)
                    window.decorView.setSystemUiVisibility(hideSystemBar())
            }
        })

        tv_bookproc1.setTextColor(Color.parseColor("#59bd97"))
        pickDate()
    }

    private fun pickDate() {
        btn_timePicker.setOnClickListener{
            tv_datebefore.setVisibility(View.INVISIBLE)
            layout_checkbook2.setVisibility(View. GONE)
            layout_checkbook3.setVisibility(View. VISIBLE)
            tv_bookproc2.setTextColor(Color.parseColor("#59bd97"))
            div_bookproc1.setBackgroundColor(Color.parseColor("#59bd97"))
            getDateTimeCalendar()
            DatePickerDialog(this,this, year,month,day).show()
        }
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month
        savedYear = year

        getDateTimeCalendar()
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        savedHour = hourOfDay
        savedMinute = minute
        tv_date.text = "$savedDay-$savedMonth-$savedYear"
    }

    fun booktosearchres(view: View) {
        startActivity(Intent(this, SearchresActivity::class.java))
        finish()
    }
    private fun hideSystemBar(): Int {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    fun backtoberandafrombooking(view: View) {
        startActivity(Intent(this, BerandaActivity::class.java))
    }
}