package edu.uph.m24si2.roomkotlindemo.data

import androidx.room3.Entity
import androidx.room3.PrimaryKey
@Entity(tableName = "mahasiswa")
data class Mahasiswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nama: String,
    val nim: String
)