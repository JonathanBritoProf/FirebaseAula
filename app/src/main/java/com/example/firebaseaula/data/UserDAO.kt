package com.example.firebaseaula.data

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UserDAO {

    fun insertUser(user : User) : Boolean {
        var isUser : Boolean =  false
        var id = "1234567"
        var database = Firebase.database.getReference("users")
        database.child(id).setValue(user).addOnCompleteListener{
            isUser = it.isSuccessful

        }
        return isUser
    }

}