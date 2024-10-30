package com.learn.email

import Email
import EmailAdapter
import android.os.Bundle
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var emailAdapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Thiết lập RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Tạo danh sách email mẫu
        val emailList = listOf(
            Email("Edurila.com", "Are you looking to learn web designing...","Are you looking to learn web designing...", "12:34 PM"),
            Email("Chris Abad", "Help make Campaign Monitor better...","Are you looking to learn web designing...", "11:22 AM"),
            Email("Tuto.com", "8h de formation gratuite...", "Are you looking to learn web designing...","11:04 AM"),
            Email("Support", "Suivi de vos services...", "Are you looking to learn web designing...","10:26 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...", "Are you looking to learn web designing...","09:42 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...", "Are you looking to learn web designing...","09:42 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...", "Are you looking to learn web designing...","09:42 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...","Are you looking to learn web designing...", "09:42 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...", "Are you looking to learn web designing...","09:42 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...", "Are you looking to learn web designing...","09:42 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...", "Are you looking to learn web designing...","09:42 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...","Are you looking to learn web designing...", "09:42 AM"),
            Email("Matt from Ionic", "Announcing the all-new Creator...", "Are you looking to learn web designing...","09:42 AM"),
        )

        emailAdapter = EmailAdapter(emailList)
        recyclerView.adapter = emailAdapter
    }
}