package com.example.bookshelf.rv

import android.os.Bundle
import android.os.Debug
import android.renderscript.Sampler.Value
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.example.bookshelf.R
import com.example.bookshelf.databinding.FragmentListLivroBinding
import com.example.bookshelf.model.AppDatabase
import com.example.bookshelf.view.LivroRepository
import com.example.bookshelf.view.LivroViewModel
import com.example.bookshelf.view.LivroViewModelFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.snapshots

// Filtrar o retorno do bostões do fragment

class FragmentListLivro : Fragment() {
    private var _binding: FragmentListLivroBinding? = null
    private val binding get() = _binding!!
    private lateinit var livroViewModel: LivroViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListLivroBinding.inflate(inflater,container,false)
        val dao = AppDatabase.getDatabase(requireContext()).livroDao()
        val repository = LivroRepository(dao)
        val factory = LivroViewModelFactory(repository)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LivroAdapter(
            onDetalhes = { livro ->
                findNavController().navigate(R.id.action_fragmentListLivro2_to_detalhesLivroFragment)
            },
            onExluir = { livro ->
                Snackbar.make(binding.root, "Gostaria de Remover o Livro?", Snackbar.LENGTH_LONG)
                    .setAction("Confirmar") {
                        livroViewModel.deletar(livro)
                    }
                    .show()
            },
            onMudarEstado = {livro ->
                Snackbar.make(binding.root, "Mudar estado do livro", Snackbar.LENGTH_LONG)
                    .setAction("Confirmar") {}
                    .show()
            }
        )

        binding.rvLivro.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }

        binding.btnAdd.setOnClickListener(){
            findNavController().navigate(R.id.action_fragmentListLivro2_to_novoLivroFragment)
        }

        livroViewModel.todosLivros.observe(viewLifecycleOwner) { livros ->
            adapter.submitList(livros)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
