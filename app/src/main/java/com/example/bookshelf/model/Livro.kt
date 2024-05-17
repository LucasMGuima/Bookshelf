package com.example.bookshelf.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Parcelize
@Entity
data class Livro(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val titulo: String?,
    val estado: String?,
    val descricao: String?
): Parcelable {
    constructor(parcel: Parcel): this (
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ){

    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(titulo)
        parcel.writeString(estado)
        parcel.writeString(descricao)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Livro> {
        override fun createFromParcel(parcel: Parcel): Livro {
            return Livro(parcel)
        }

        override fun newArray(size: Int): Array<Livro?> {
            return arrayOfNulls(size)
        }
    }
}

annotation class Parcelize