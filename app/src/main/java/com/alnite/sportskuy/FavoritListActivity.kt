package com.alnite.sportskuy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alnite.sportskuy.fragments.BadmintonFragment
import com.alnite.sportskuy.fragments.BasketFragment
import com.alnite.sportskuy.fragments.FutsalFavoritFragment
import com.alnite.sportskuy.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_favoritlist.*

class FavoritListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritlist)
        setUpTabs()
    }
    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(FutsalFavoritFragment(), "Futsal")
        adapter.addFragment(BasketFragment(), "Basket")
        adapter.addFragment(BadmintonFragment(), "Badminton")
        ViewPager.adapter = adapter
        tabs.setupWithViewPager(ViewPager)

        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_outline_sports_soccer_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_outline_sports_basketball_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_outline_sports_tennis_24)

    }
}