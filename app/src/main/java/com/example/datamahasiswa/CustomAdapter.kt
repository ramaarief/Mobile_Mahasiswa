package com.example.datamahasiswa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter (val userList: ArrayList<User>): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val user: User=userList[position]
        holder?.textViewId?.text = user.id
        holder?.textViewNama?.text = user.nama
        holder?.textViewNomer?.text = user.nomer
        holder?.textViewAlamat?.text = user.alamat

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v= LayoutInflater.from(parent?.context).inflate(R.layout.list_layout, parent, false)
        return  ViewHolder(v)

    }

    override fun getItemCount(): Int {

        return userList.size
    }

    class  ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val textViewId = itemView.findViewById(R.id.textViewId) as TextView
        val textViewNama = itemView.findViewById(R.id.textViewNama) as TextView
        val textViewNomer = itemView.findViewById(R.id.textViewNomer) as TextView
        val textViewAlamat = itemView.findViewById(R.id.textViewAlamat) as TextView

    }

}