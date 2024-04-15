package com.example.bookshelf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.databinding.FragmentListLivroBinding
import com.example.bookshelf.databinding.FragmentNovoLivroBinding

class NovoLivroFragment : Fragment() {
    private var _binding: FragmentNovoLivroBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNovoLivroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()
    }

    private fun listeners(){
        binding.btnAdicionar.setOnClickListener(){
            getDados()
            findNavController().navigate(R.id.action_novoLivroFragment_to_fragmentListLivro2)
        }
        binding.btnVoltar.setOnClickListener(){
            findNavController().navigate(R.id.action_novoLivroFragment_to_fragmentListLivro2)
        }
    }

    private fun getDados(){
        val titulo = binding.inpTituloLivro.text.toString().trim()
        val descricao = binding.descricaoLivro.text.toString()
        val estado = binding.radioGroupEstado.checkedRadioButtonId.toString()

        println("Titulo: ${titulo} \nDesc: ${descricao} \nEstado: ${estado}")
    }
}