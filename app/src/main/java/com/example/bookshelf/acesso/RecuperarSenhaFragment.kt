package com.example.bookshelf.acesso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.R
import com.example.bookshelf.databinding.FragmentRecuperarSenhaBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class RecuperarSenhaFragment : Fragment() {
    private var _binding:FragmentRecuperarSenhaBinding? = null
    private val binding get() = _binding !!

    private lateinit var firebase: Firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebase = Firebase
        _binding = FragmentRecuperarSenhaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val auth = FirebaseAuth.getInstance()
        binding.btnRecSenha.setOnClickListener(){
            val email = binding.inpEmail.text.toString().trim()

            if (email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Email de redefinição enviado.", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_recuperarSenhaFragment_to_loginFragment)
                        } else {
                            Toast.makeText(context, "Falha ao enviar email de redefinição.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Por favor, insira seu email.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}