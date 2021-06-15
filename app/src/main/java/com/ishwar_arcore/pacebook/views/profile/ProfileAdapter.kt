package com.ishwar_arcore.pacebook.views.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ishwar_arcore.pacebook.R
import com.squareup.picasso.Picasso

class ProfileAdapter(val model: ArrayList<model>, val context: ProfileFragment):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View=LayoutInflater.from(parent.context).inflate(R.layout.profile_item,parent,false)
            return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder:RecyclerView.ViewHolder,position: Int) {

        (holder as ProfileViewHolder).username.text = model[position].username
        holder.text.text = model[position].text
        Picasso.get().load(model[position].photo).into(holder.photo)
    }

    override fun getItemCount(): Int {
       return model.size
    }

    class ProfileViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val username:TextView=itemView.findViewById(R.id.username)
        val text:TextView=itemView.findViewById(R.id.text)
        val photo:ImageView=itemView.findViewById(R.id.photo)

    }

}