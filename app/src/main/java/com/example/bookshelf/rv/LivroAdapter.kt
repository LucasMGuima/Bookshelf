package com.example.bookshelf.rv

import android.view.LayoutInflater
import com.example.bookshelf.model.Livro
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.bookshelf.databinding.ItemLivroBinding

// Criar variaveis para cada botão, cada variavel armazena uma instancia do objeto

class LivroAdapter(
    private val onDetalhes: (Livro) -> Unit,
    private val onMudarEstado: (Livro) -> Unit,
    private val onExluir: (Livro) -> Unit
): ListAdapter<Livro, LivroAdapter.LivroViewHolder>(LivroDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        val binding =ItemLivroBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return LivroViewHolder(binding, onDetalhes, onMudarEstado, onExluir)
    }

    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val livro = getItem(position)

        holder.bind(livro)
    }

    class LivroViewHolder(
        private val binding: ItemLivroBinding,
        private val onDetalhes: (Livro) -> Unit,
        private val onMudarEstado: (Livro) -> Unit,
        private val onExluir: (Livro) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(livro: Livro) {
            binding.apply {
                textTituloLivro.text = livro.titulo
                statuLivro.text = livro.estado

                btnDetalhes.setOnClickListener { onDetalhes(livro) }
                btnExcluir.setOnClickListener { onExluir(livro) }
                btnMudarEstado.setOnClickListener { onMudarEstado(livro) }
            }
        }
    }
}

class LivroDiffCallback : DiffUtil.ItemCallback<Livro>() {
    override fun areItemsTheSame(oldItem: Livro, newItem: Livro): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Livro, newItem: Livro): Boolean {
        return oldItem == newItem
    }
}