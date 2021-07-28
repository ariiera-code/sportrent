package com.alnite.sportskuy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

@Suppress("DEPRECATION")
class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
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
    }

    fun searchtoberanda(view: View) {
        startActivity(Intent(Intent(this, BerandaActivity::class.java)))
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

    fun searchtoresult(view: View) {
        startActivity(Intent(this, SearchresActivity::class.java))
    }
}