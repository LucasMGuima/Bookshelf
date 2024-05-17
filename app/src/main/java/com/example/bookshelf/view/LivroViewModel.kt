package com.example.bookshelf.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookshelf.model.Livro
import kotlinx.coroutines.launch

class LivroViewModel(private val repository: LivroRepository): ViewModel() {
    val todosLivros: LiveData<List<Livro>> = repository.todosLivros

    fun inserir(livro: Livro) = viewModelScope.launch {
        repository.inserir(livro)
    }

    fun atualizar(livro: Livro) = viewModelScope.launch {
        repository.atualizar(livro)
    }

    fun deletar(livro: Livro) = viewModelScope.launch {
        repository.deletar(livro)
    }
}