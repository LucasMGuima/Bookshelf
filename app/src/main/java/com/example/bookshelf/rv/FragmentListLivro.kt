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

    private lateinit var livroAdapter: LivroAdapter
    private lateinit var database: DatabaseReference
    private val livroList = mutableListOf<Livro>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        database = FirebaseDatabase.getInstance().getReference("bookshelf")
        _binding = FragmentListLivroBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getLivros()
        livroAdapter = LivroAdapter(livroList
                onDetalhes = {

        })

        binding.rvLivro.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLivro.setHasFixedSize(true)
        binding.rvLivro.adapter = livroAdapter

        binding.btnAdd.setOnClickListener(){
            findNavController().navigate(R.id.action_fragmentListLivro2_to_novoLivroFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getLivros(){
        database.orderByKey().addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    livroList.clear()
                    for(livroSnapshot in snapshot.children){
                        val livro = livroSnapshot.getValue(Livro::class.java)
                        if (livro != null) {
                            livroList.add(livro)
                        }
                    }
                    livroAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Erro ao carregar os dados", Toast.LENGTH_LONG).show()
                }
            }
        )
    }
}
