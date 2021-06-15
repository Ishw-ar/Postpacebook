package com.ishwar_arcore.pacebook.views.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.profile.ProfileAdapter
import com.ishwar_arcore.pacebook.views.profile.model
import kotlinx.android.synthetic.main.fragment_notification.*
import kotlinx.android.synthetic.main.fragment_profile.*

class NotificationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val Notifications: ArrayList<NotificationModel> = ArrayList()
        for (i in 1..10) {
            Notifications.add(
                NotificationModel(
                    "varsha_" + i,
                    "varsha image",
                    "https://picsum.photos/600/300?random&" + i
                )
            )
        }

        notificationRecyclerView.layoutManager = LinearLayoutManager(context)
        notificationRecyclerView.adapter = NotificationAdapter(Notifications, this)
    }

    companion object {
        fun newInstance() = NotificationFragment()
    }
}