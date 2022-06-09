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

class FragmentRegister : Fragment(R.layout.fragment_register) {

    lateinit var accessViewModel: AccessViewModel
    lateinit var emailTxt : EditText
    lateinit var passwordTxt : EditText
    lateinit var registerbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        accessViewModel =  ViewModelProvider(this)[AccessViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailTxt = view.findViewById(R.id.txtEmail)
        passwordTxt =  view.findViewById(R.id.txtPassword)
        registerbtn =  view.findViewById(R.id.btnRegister)
        setupListener()
        setupObserver()

    }

    private fun setupListener() {
        registerbtn.setOnClickListener {
            accessViewModel.onCreateUser(emailTxt.text.toString(),passwordTxt.text.toString())
        }
    }
    private fun setupObserver() {
        accessViewModel.createUserLiveData.observe(viewLifecycleOwner){
            if(it){
               findNavController().navigate(R.id.action_fragmentRegister_to_fragmentLogin)
               Toast.makeText(requireContext(),"Usuario Criado com sucesso",Toast.LENGTH_SHORT).show()
            } else {
               Toast.makeText(requireContext(),"Erro ao Criar usuário, tente novamente",Toast.LENGTH_SHORT).show()
            }
        }
    }

}