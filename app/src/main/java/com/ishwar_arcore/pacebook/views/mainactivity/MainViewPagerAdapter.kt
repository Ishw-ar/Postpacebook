package com.ishwar_arcore.pacebook.views.mainactivity

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainViewPagerAdapter(activity: MainActivity, list : List<Fragment>): FragmentStateAdapter(activity) {

    val fragList = list

    override fun getItemCount(): Int {
        return fragList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragList[position]
    }
}