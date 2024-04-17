package com.example.bookshelf.acesso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.R
import com.example.bookshelf.databinding.FragmentLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebase: Firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebase = Firebase
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()
    }

    //Functions
    private fun listeners(){
        binding.btnLogar.setOnClickListener(){
            val email = binding.inpEmail.text.toString().trim()
            val senha = binding.inpSenha.text.toString()

            login(email, senha)
        }

        binding.btnNovoUsuario.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_novoUsuarioFragment)
        }
        binding.btnRecSenha.setOnClickListener(){
            findNavController().navigate(R.id.action_loginFragment_to_recuperarSenhaFragment)
        }
    }

    private fun login(email: String, senha: String){
        val auth = firebase.auth
        auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener{ task ->
            if (task.isSuccessful){
                findNavController().navigate(R.id.action_loginFragment_to_fragmentListLivro2)
            }else{
                Toast.makeText(requireContext(), "Email ou Senha inválidos", Toast.LENGTH_LONG).show()
            }
        }
    }
}