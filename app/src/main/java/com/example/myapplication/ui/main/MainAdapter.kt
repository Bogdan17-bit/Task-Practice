package com.example.myapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListRowMainBinding
import com.example.myapplication.models.response.User

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var userList = mutableListOf<User>()

    fun setUsers(users : List<User>) {
        userList = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListRowMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.textView.text = user.getShortName()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MainViewHolder(val binding: ListRowMainBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}

