package com.example.bookshelf.acesso

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.R
import com.example.bookshelf.databinding.FragmentNovoUsuarioBinding

class NovoUsuarioFragment : Fragment() {

    private var _binding: FragmentNovoUsuarioBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentNovoUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCadastrar.setOnClickListener(){
            val email = binding.inpEmail.text.toString().trim()
            val senha = binding.inpSenha.text.toString().trim()
            val confSenha = binding.inpConfSenha.text.toString().trim()

            println("Email: ${email} Senha: ${senha} Conf.Senha: ${confSenha}")

            findNavController().navigate(R.id.action_novoUsuarioFragment_to_loginFragment)
        }
    }
}