package com.erdalguzel.retrofit_sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erdalguzel.retrofit_sample.`interface`.OnGetUsers
import com.erdalguzel.retrofit_sample.model.User
import com.erdalguzel.retrofit_sample.viewmodel.UserAdapter
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: UserAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

    private fun loadUserList(users: List<User>?) {
        adapter = UserAdapter(users!!)
        recyclerView = findViewById(R.id.userRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapter
    }
}