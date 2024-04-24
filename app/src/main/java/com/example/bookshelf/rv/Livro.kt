package com.example.bookshelf.rv

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Livro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String?,
    val estado: String?,
    val descricao: String?
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )
}