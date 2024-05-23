package com.example.bookshelf.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Livro::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun livroDao(): LivroDao

    companion object {
        // Volatile para garantir que a instância seja visível para todas as threads
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // Função para retornar a instância do banco de dados
        fun getDatabase(context: Context): AppDatabase {
            // Se a instância não é nula, então retorna ela,
            // se for nula, cria a instância.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "bookshelf"
                ).fallbackToDestructiveMigration() // Estratégia para lidar com versões incompatíveis de esquemas
                    .build()
                INSTANCE = instance
                // Retorna a instância criada
                instance
            }
        }
    }
}