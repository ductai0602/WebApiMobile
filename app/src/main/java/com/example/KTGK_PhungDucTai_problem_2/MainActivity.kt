package com.example.KTGK_PhungDucTai_problem_2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)){
            v, inset ->
            val systemBars = inset.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            inset
        }

        recyclerView = findViewById(R.id.recyclerView)

        postAdapter = PostAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postAdapter

        getPosts()
    }

    private fun getPosts(){
        ApiClient.api.getTodos().enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>){
                if(response.isSuccessful){
                    response.body()?.let{
                        posts -> postAdapter.submitList(posts)
                    }
                }else {
                    Toast.makeText(this@MainActivity, "Failed to get posts", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        getPosts()
    }
}