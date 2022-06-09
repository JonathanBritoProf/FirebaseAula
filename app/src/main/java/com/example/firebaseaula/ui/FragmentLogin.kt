package com.example.firebaseaula.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.firebaseaula.R
import com.example.firebaseaula.viewmodel.AccessViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.sign

class FragmentLogin : Fragment(R.layout.fragment_login) {

    lateinit var accessViewModel: AccessViewModel
    lateinit var emailTxt : EditText
    lateinit var passwordTxt : EditText
    lateinit var signInbtn : Button
    lateinit var registerBtn :  Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accessViewModel = ViewModelProvider(this)[AccessViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailTxt =  view.findViewById(R.id.email)
        passwordTxt = view.findViewById(R.id.password)
        signInbtn = view.findViewById(R.id.btnlogin)
        registerBtn =  view.findViewById(R.id.btncadastrar)
        setupListeners()
        setupObserver()
    }

    private fun setupListeners() {
        signInbtn.setOnClickListener {
            accessViewModel.onSignIn(emailTxt.text.toString(),passwordTxt.text.toString())
        }

        registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentLogin_to_fragmentRegister)
        }
    }

    private fun setupObserver() {
        accessViewModel.userAuthLiveData.observe(viewLifecycleOwner){
            if(it) {
                findNavController().navigate(R.id.action_fragmentLogin_to_fragmentMain)
                Toast.makeText(requireContext(),"Usuario criado com sucesso", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(),"Erro ao realizar Login, verifique os dados digitados", Toast.LENGTH_SHORT).show()
            }
        }
    }
}