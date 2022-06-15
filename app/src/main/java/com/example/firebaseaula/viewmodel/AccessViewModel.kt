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
        var user = User(name,lastname,email,password)

        registerTask.addOnCompleteListener {
            var id = firebaseAuth.currentUser?.uid
            if(registerTask.isSuccessful) {
                var dao = UserDAO()
                onUserRequestToRegister.value =  dao.insertUser(user)
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