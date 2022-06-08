package com.example.firebaseaula

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseaula.viewmodel.AccessViewModel

class MainActivity : AppCompatActivity() {

    lateinit var accessViewModel : AccessViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        accessViewModel =  ViewModelProvider(this)[AccessViewModel::class.java]
        setupListeners()
        setupObservers()
    }

    fun setupListeners() {
        findViewById<TextView>(R.id.txt).setOnClickListener{
            accessViewModel.onCreateUser("a2luno123456@gmail.com","12345678")
        }
    }

    fun setupObservers() {
        accessViewModel.emailAuthLiveData.observe(this){
            if (it){
                Toast.makeText(this,"Sucesso",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Erro",Toast.LENGTH_SHORT).show()
            }
        }
    }


}