@file:Suppress("DEPRECATION")

package com.alnite.sportskuy

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.alnite.sportskuy.fragments.Test1Fragment
import com.alnite.sportskuy.fragments.Test2Fragment
import com.alnite.sportskuy.fragments.Test3Fragment
import com.alnite.sportskuy.fragments.Test4Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.alnite.sportskuy.fragments.BadmintonFragment
import com.alnite.sportskuy.fragments.BasketFragment
import com.alnite.sportskuy.fragments.FutsalFragment
import com.alnite.sportskuy.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_beranda.*
import kotlinx.android.synthetic.main.fragment_booking.*
import kotlinx.android.synthetic.main.fragment_favorit.*

@Suppress("DEPRECATION")
class BerandaActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager
    private lateinit var adapter: ViewPagerAdapter
    lateinit var auth: FirebaseAuth
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

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

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        val Test1Fragment = Test1Fragment()
        val Test2Fragment = Test2Fragment()
        val Test3Fragment = Test3Fragment()
        val Test4Fragment = Test4Fragment()

        makeCurrentFragment(Test1Fragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_navberanda -> makeCurrentFragment(Test1Fragment)
                R.id.ic_navfav -> makeCurrentFragment(Test2Fragment)
                R.id.ic_navbooking -> makeCurrentFragment(Test3Fragment)
                R.id.ic_navsetting -> makeCurrentFragment(Test4Fragment)

            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    fun berandatofutsal(view: View) {
        startActivity(Intent(this, FutsalActivity::class.java))
    }

    fun logout(view: View) {
        auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun whatsappweb(view: View) {
        startActivity(Intent(this, Whatsapp::class.java))
    }

    fun berandatosearch(view: View) {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            window.decorView.setSystemUiVisibility(hideSystemBar())
        }
    }
    private fun hideSystemBar(): Int {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    fun booklisttothat(view: View) {
        btnUnfinished1.setOnClickListener {
            startActivity(Intent(this, BookingListActivity::class.java))
        }
    }

    fun favlisttothat(view: View) {
        btnUnfinished2.setOnClickListener{
            startActivity(Intent(this, FavoritListActivity::class.java))
        }
    }
}
//TARGET : DETAILS TEMPAT, CEK JADWAL, BASKET, BADMINTON