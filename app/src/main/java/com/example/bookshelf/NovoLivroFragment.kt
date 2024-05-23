@file:Suppress("CAST_NEVER_SUCCEEDS")

package com.example.bookshelf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.bookshelf.databinding.FragmentNovoLivroBinding
import com.example.bookshelf.model.AppDatabase
import com.example.bookshelf.model.Livro
import com.example.bookshelf.view.LivroRepository
import com.example.bookshelf.view.LivroViewModel
import com.example.bookshelf.view.LivroViewModelFactory
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

@Suppress("NAME_SHADOWING", "KotlinConstantConditions")
class NovoLivroFragment : Fragment() {
    private var _binding: FragmentNovoLivroBinding? = null
    private val binding get() = _binding!!
    private lateinit var livroViewModel: LivroViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dao = AppDatabase.getDatabase(requireContext()).livroDao()
        val repository = LivroRepository(dao)
        val factory = LivroViewModelFactory(repository)
        livroViewModel = ViewModelProvider(this, factory).get(LivroViewModel::class.java)
        _binding = FragmentNovoLivroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listeners()
    }

    private fun listeners(){
        binding.btnAdicionar.setOnClickListener(){
            addNovoLivro()
            findNavController().navigate(R.id.action_novoLivroFragment_to_fragmentListLivro2)
        }
        binding.btnVoltar.setOnClickListener(){
            findNavController().navigate(R.id.action_novoLivroFragment_to_fragmentListLivro2)
        }
    }

    private fun addNovoLivro(){
        val titulo = binding.inpTituloLivro.text.toString().trim()
        val descricao = binding.descricaoLivro.text.toString()
        val estado = when (binding.radioGroupEstado.checkedRadioButtonId) {
            R.id.estado_lendo -> "Lendo"
            R.id.estado_lido -> "Lido"
            else -> ""
        }

        if(titulo.isNotEmpty() && descricao.isNotEmpty() && estado.isNotEmpty()){
            val livroId = database.push().key ?: return
            val livro = Livro(id, titulo, estado, descricao)
            database.child(livroId).setValue(livro)
                .addOnCompleteListener {
                    Toast.makeText(context, "Livro salvo com sucesso!", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener(){
                    Toast.makeText(context, "Falha ao salvar o livro.", Toast.LENGTH_SHORT).show()
                }
        }else{
            Toast.makeText(context, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
        }
    }
}