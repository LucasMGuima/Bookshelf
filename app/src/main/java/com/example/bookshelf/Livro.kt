package com.example.bookshelf

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Livro(
    val id: String,
    val titulo: String,
    val estado: String,
    val descricao: String
):Parcelable

