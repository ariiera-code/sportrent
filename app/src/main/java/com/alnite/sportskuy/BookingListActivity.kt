package com.alnite.sportskuy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alnite.sportskuy.fragments.CancelledFragment
import com.alnite.sportskuy.fragments.MendatangFragment
import com.alnite.sportskuy.fragments.SelesaiFragment
import com.alnite.sportskuy.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_booking_list.*

class BookingListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking_list)

        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MendatangFragment(),  "Mendatang")
        adapter.addFragment(SelesaiFragment(), "Selesai")
        adapter.addFragment(CancelledFragment(), "Batal")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_room_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_done_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_clear_24)


    }
}