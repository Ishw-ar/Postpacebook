package com.ishwar_arcore.pacebook.views.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishwar_arcore.pacebook.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val models: ArrayList<model> = ArrayList()
        for (i in 1..10) {
            models.add(
                model(
                    "varsha_" + i,
                    "varsha image",
                    "https://picsum.photos/600/300?random&" + i
                )
            )
        }

        profileRecyclerView.layoutManager = LinearLayoutManager(context)
        profileRecyclerView.adapter = ProfileAdapter(models, this)
    }


    companion object {
        fun newInstance() = ProfileFragment()
    }
}

