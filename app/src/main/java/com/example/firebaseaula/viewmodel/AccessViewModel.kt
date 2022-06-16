package com.example.firebaseaula.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseaula.data.User
import com.example.firebaseaula.data.UserDAO
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AccessViewModel : ViewModel() {

    var firebaseAuth = Firebase.auth

    private var onUserRequestToRegister = MutableLiveData<Boolean>()
    var createUserLiveData : LiveData<Boolean> = onUserRequestToRegister

    private var onUserRequestToSignIn = MutableLiveData<Boolean>()
    var userAuthLiveData : LiveData<Boolean> = onUserRequestToSignIn

    fun onCreateUser (name : String, lastname :  String, email : String , password : String) {
        var registerTask = firebaseAuth.createUserWithEmailAndPassword(email,password)

        registerTask.addOnCompleteListener {
            if(registerTask.isSuccessful) {
                var user = User(firebaseAuth.currentUser?.uid!!, name,lastname,email,password)
                UserDAO().insertUser(user)
                onUserRequestToRegister.value =  registerTask.isSuccessful
            }
        }
    }

    fun onSignIn(email : String , password : String) {
        var authTask = firebaseAuth.signInWithEmailAndPassword(email,password)

        authTask.addOnCompleteListener {
            onUserRequestToRegister.value = authTask.isSuccessful
        }
    }

}