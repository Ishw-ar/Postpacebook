package com.ishwar_arcore.pacebook.views.mainactivity


import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.home.HomeFragment
import com.ishwar_arcore.pacebook.views.marketplace.MarketplaceFragment
import com.ishwar_arcore.pacebook.views.menu.MenuFragment
import com.ishwar_arcore.pacebook.views.notification.NotificationFragment
import com.ishwar_arcore.pacebook.views.profile.ProfileFragment
import com.ishwar_arcore.pacebook.views.watch.WatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewPagerAdapter: MainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
    }

    private fun initViewPager() {

        val list = arrayListOf<Fragment>(
            HomeFragment.newInstance(),
            WatchFragment.newInstance(),
            MarketplaceFragment.newInstance(),
            ProfileFragment.newInstance(),
            NotificationFragment.newInstance(),
            MenuFragment.newInstance()
        )

        viewPagerAdapter = MainViewPagerAdapter(this,list)
        mainViewPager.adapter = viewPagerAdapter

        //val draw = Drawable.createFromResourceStream(R.drawable.icon_home,)

        TabLayoutMediator(mainTabLayout,mainViewPager){ tab, position ->
            tab.icon = when(position){
                0 -> ContextCompat.getDrawable(this,R.drawable.icon_home)
                1 -> ContextCompat.getDrawable(this,R.drawable.icon_video)
                2 -> ContextCompat.getDrawable(this,R.drawable.icon_shop)
                3 -> ContextCompat.getDrawable(this,R.drawable.icon_profile)
                4 -> ContextCompat.getDrawable(this,R.drawable.icon_notification)
                5 -> ContextCompat.getDrawable(this,R.drawable.icon_menu)
                else -> ContextCompat.getDrawable(this,R.drawable.icon_home)
            }

        }.attach()
    }
}