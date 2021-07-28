package com.alnite.sportskuy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alnite.sportskuy.fragments.BookActivity
import kotlinx.android.synthetic.main.activity_futsal.*
import kotlinx.android.synthetic.main.rv_futsallist.*

@Suppress("DEPRECATION")
class FutsalActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RvFutsalAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_futsal)

        layoutManager = LinearLayoutManager(this)
        recyclerFutsal.layoutManager = layoutManager

        adapter = RvFutsalAdapter()
        recyclerFutsal.adapter = adapter

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

    fun masuk_beranda(view: View) {
        startActivity(Intent(this, BerandaActivity::class.java))
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
}