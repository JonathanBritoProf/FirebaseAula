package com.example.firebaseaula.data

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class User (var name : String,
                 var lastname :  String,
                 var email : String,
                 var password : String)