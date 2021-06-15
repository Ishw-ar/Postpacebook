package com.ishwar_arcore.pacebook.views.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.AddPost.AddPostActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       dummybutton.setOnClickListener {
           val intent= Intent(activity,AddPostActivity::class.java)
           startActivity(intent)
       }


    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}