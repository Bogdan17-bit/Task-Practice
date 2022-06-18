package com.example.myapplication.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ListRowMainBinding
import com.example.myapplication.models.response.User

class MainAdapter: RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    private var userList = mutableListOf<User>()

    fun getUsers() : List<User> {
        return userList
    }

    fun setUsers(users : List<User>) {
        userList += users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListRowMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.textView.text = user.getShortName()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MainViewHolder(val binding: ListRowMainBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.textView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}

