package com.example.bookshelf.rv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookshelf.databinding.FragmentListLivroBinding

class FragmentListLivro : Fragment() {
    private var _binding: FragmentListLivroBinding? = null
    private val binding get() = _binding!!

    private lateinit var livroAdapter: LivroAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListLivroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        livroAdapter = LivroAdapter(getLivros())

        binding.rvLivro.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLivro.setHasFixedSize(true)
        binding.rvLivro.adapter = livroAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getLivros() = listOf(
        Livro("0", "Mistborn","Lendo", "... ... ..."),
        Livro("1", "Senhor dos Aneis","Lido", "... ... ..."),
        Livro("2", "Locked in Time","Lido", "... ... ..."),
        Livro("3", "The Bodysnatchers","Lendo", "... ... ..."),
        Livro("4", "O Fim da Eternidade","Lido", "... ... ..."),
        Livro("5", "A Maquina do Tempo","Lido", "... ... ...")
    )
}
