package com.example.bookshelf.view

import androidx.lifecycle.LiveData
import com.example.bookshelf.model.LivroDao
import com.example.bookshelf.model.Livro

class LivroRepository(private val livroDao: LivroDao) {

    val todosLivros: LiveData<List<Livro>> = livroDao.todosLivros()

    suspend fun inserir(livro: Livro) {
        livroDao.inserirLivro(livro)
    }

    suspend fun atualizar(livro: Livro) {
        livroDao.atualizarLivro(livro)
    }

    suspend fun deletar(livro: Livro) {
        livroDao.deletarLivro(livro)
    }
}