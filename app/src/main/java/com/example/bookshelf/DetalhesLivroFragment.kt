package com.example.bookshelf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.bookshelf.databinding.FragmentDetalhesLivroBinding

class DetalhesLivroFragment : Fragment() {
    private var _binding: FragmentDetalhesLivroBinding? = null
    private val binding get() = _binding!!
    val args: DetalhesLivroFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetalhesLivroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val livro = args.livro

        binding.tvTituloLivro.setText(livro.titulo)
        binding.tvDescricao.setText(livro.descricao)
        binding.tvEstado.setText(livro.estado)
    }
}