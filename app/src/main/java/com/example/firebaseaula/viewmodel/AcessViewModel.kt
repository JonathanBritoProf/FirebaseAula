package com.example.firebaseaula.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AcessViewModel : ViewModel() {

    var onUserRequestToRegister = MutableLiveData<Boolean>()
    var EmailAuthLiveData : LiveData<Boolean> = onUserRequestToRegister

    fun onRequestToCreateUser (email : String , password : String) {
       var firebaseAuth = Firebase.auth
       var task = firebaseAuth.createUserWithEmailAndPassword(email,password)
       task.addOnSuccessListener {
           onUserRequestToRegister.value = true
       }
       task.addOnFailureListener {
           onUserRequestToRegister.value = false
       }
    }

}