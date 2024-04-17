package com.example.bookshelf.acesso

import android.hardware.SensorAdditionalInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.R
import com.example.bookshelf.databinding.FragmentNovoUsuarioBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class NovoUsuarioFragment : Fragment() {

    private var _binding: FragmentNovoUsuarioBinding? = null
    private val binding get() = _binding!!

    private lateinit var firebase: Firebase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        firebase = Firebase
        _binding =  FragmentNovoUsuarioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCadastrar.setOnClickListener(){
            val email = binding.inpEmail.text.toString().trim()
            val senha = binding.inpSenha.text.toString().trim()
            val confSenha = binding.inpConfSenha.text.toString().trim()

            verifcacao(email, senha, confSenha)
        }

        binding.btnVoltar.setOnClickListener(){
            findNavController().navigate(R.id.action_novoUsuarioFragment_to_loginFragment)
        }
    }

    private fun verifcacao(email: String, senha: String, confSenha: String){
        if (email.isEmpty() || senha.isEmpty() || confSenha.isEmpty()) {
            Toast.makeText(context, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return
        }

        if(senha.length < 6){
            Toast.makeText(context, "Senha deve ter 6 caracteres ou mais.", Toast.LENGTH_LONG).show()
            return
        }

        if(senha != confSenha){
            Toast.makeText(context, "As senhas não conferem.", Toast.LENGTH_LONG).show()
            return
        }

        cadastrarUsuario(email, senha)
    }

    private fun cadastrarUsuario(email: String, senha: String){
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(requireActivity()){task ->
            if(task.isSuccessful){
                val user = auth.currentUser
                user?.sendEmailVerification()?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(context, "Veirificação de email enviada.", Toast.LENGTH_LONG).show()
                    }
                }
                findNavController().navigate(R.id.action_novoUsuarioFragment_to_loginFragment)
            }else{
                Toast.makeText(context, "Falha no cadastro: ${task.exception?.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}