package com.example.bookshelf.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LivroDao {
    @Insert
    suspend fun inserirLivro(livro: Livro)

    @Update
    suspend fun atualizarLivro(livro: Livro)

    @Delete
    suspend fun deletarLivro(livro: Livro)

    @Query("SELECT * FROM bookshelf")
    fun todosLivros(): LiveData<List<Livro>>
}