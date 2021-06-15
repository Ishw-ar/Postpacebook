package com.ishwar_arcore.pacebook.views.notification

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R
import com.ishwar_arcore.pacebook.views.profile.ProfileFragment
import com.ishwar_arcore.pacebook.views.profile.model
import com.squareup.picasso.Picasso

class NotificationAdapter(val NotificationModel: ArrayList<NotificationModel>, val context: NotificationFragment):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.notification_item_layout,parent,false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as NotificationViewHolder ).notificationname.text = NotificationModel[position].notificationname
        holder.Description.text = NotificationModel[position].Description
        Picasso.get().load(NotificationModel[position].Image).into(holder.Image)
    }

    override fun getItemCount(): Int {
        return NotificationModel.size
    }

    class NotificationViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val notificationname: TextView =itemView.findViewById(R.id.notiUsername)
        val Description: TextView =itemView.findViewById(R.id.notiDescription)
        val Image: ImageView =itemView.findViewById(R.id.notiProfile_image)

    }

}