package com.example.firebaseaula.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccessViewModel : ViewModel() {

    var firebaseAuth = Firebase.auth

    var onUserRequestToRegister = MutableLiveData<Boolean>()
    var createUserLiveData : LiveData<Boolean> = onUserRequestToRegister

    var onUserRequestToSignIn = MutableLiveData<Boolean>()
    var userAuthLiveData : LiveData<Boolean> = onUserRequestToSignIn

    fun onCreateUser (email : String , password : String) {
        var registerTask = firebaseAuth.createUserWithEmailAndPassword(email,password)

        registerTask.addOnSuccessListener {
            onUserRequestToRegister.value = registerTask.isSuccessful
        }

        registerTask.addOnFailureListener {
            onUserRequestToRegister.value = registerTask.isSuccessful
        }

    }

    fun onSignIn(email : String , password : String) {
        var authTask = firebaseAuth.signInWithEmailAndPassword(email,password)

        authTask.addOnSuccessListener {
            onUserRequestToSignIn.value = authTask.isSuccessful
        }

        authTask.addOnFailureListener {
            onUserRequestToSignIn.value = authTask.isSuccessful
        }

    }

}