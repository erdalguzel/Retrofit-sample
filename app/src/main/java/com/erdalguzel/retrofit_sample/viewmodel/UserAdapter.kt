package com.erdalguzel.retrofit_sample.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.erdalguzel.retrofit_sample.R
import com.erdalguzel.retrofit_sample.model.User
import com.erdalguzel.retrofit_sample.view.MainFragmentDirections

class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view: View = itemView
        var realNameTextView: TextView
        var userNameTextView: TextView
        var userEmailTextView: TextView

        init {
            realNameTextView = view.findViewById(R.id.realNameTextView)
            userNameTextView = view.findViewById(R.id.userNameTextView)
            userEmailTextView = view.findViewById(R.id.userEmailTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_row, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.realNameTextView.text = users[position].name
        holder.userNameTextView.text = users[position].username
        holder.userEmailTextView.text = users[position].email

        holder.view.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToAddressFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }
}