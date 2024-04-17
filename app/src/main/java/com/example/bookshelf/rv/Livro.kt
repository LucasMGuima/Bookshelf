package com.example.bookshelf.rv

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Livro(
    val titulo: String,
    val estado: String,
    val descricao: String
):Parcelable{
    constructor() : this(
        "",
        "",
        ""
    )
}

