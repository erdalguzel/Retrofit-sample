package com.erdalguzel.retrofit_sample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erdalguzel.retrofit_sample.R
import com.erdalguzel.retrofit_sample.RetrofitClient
import com.erdalguzel.retrofit_sample.`interface`.OnGetUsers
import com.erdalguzel.retrofit_sample.model.User
import com.erdalguzel.retrofit_sample.viewmodel.UserAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {

    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = RetrofitClient().getRetrofitInstance()?.create(OnGetUsers::class.java)
        val call: Call<List<User>> = service!!.getAllUsers()

        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                loadUserList(response.body())
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Snackbar.make(recyclerView, "Unable to load users", Snackbar.LENGTH_SHORT).show()
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun loadUserList(users: List<User>?) {
        adapter = UserAdapter(users!!)
        recyclerView = requireView().findViewById(R.id.userRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }
}