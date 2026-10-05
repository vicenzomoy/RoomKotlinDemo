package edu.uph.m24si2.roomkotlindemo.data

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insert(mahasiswa: Mahasiswa)
    @Update
    suspend fun update(mahasiswa: Mahasiswa)
    @Delete
    suspend fun delete(mahasiswa: Mahasiswa)
    @Query("SELECT * FROM mahasiswa ORDER BY id DESC")
    suspend fun getAll(): List<Mahasiswa>
}
