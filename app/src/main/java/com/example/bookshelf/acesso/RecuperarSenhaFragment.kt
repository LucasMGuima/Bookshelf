package com.example.bookshelf.acesso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.R
import com.example.bookshelf.databinding.FragmentRecuperarSenhaBinding

class RecuperarSenhaFragment : Fragment() {
    private var _binding:FragmentRecuperarSenhaBinding? = null
    private val binding get() = _binding !!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecuperarSenhaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRecSenha.setOnClickListener(){
            val email = binding.inpEmail.text.toString().trim()

            println("Email: ${email}")

            findNavController().navigate(R.id.action_recuperarSenhaFragment_to_loginFragment)
        }
    }
}