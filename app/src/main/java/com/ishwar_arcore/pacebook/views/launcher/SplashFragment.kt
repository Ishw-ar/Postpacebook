package com.ishwar_arcore.pacebook.views.launcher

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.ishwar_arcore.pacebook.R

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_splash, container, false)

        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_mainActivity22,null,
            NavOptions.Builder().setPopUpTo(R.id.splashFragment,true).build())
        }, 2500)

        return view
    }
}