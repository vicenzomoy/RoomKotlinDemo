package edu.uph.m24si2.roomkotlindemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uph.m24si2.roomkotlindemo.data.AppDatabase
import edu.uph.m24si2.roomkotlindemo.data.Mahasiswa
import edu.uph.m24si2.roomkotlindemo.data.MahasiswaDao
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var etNama: EditText
    private lateinit var etNim: EditText
    private lateinit var btnSimpan: Button
    private lateinit var btnBatal: Button
    private lateinit var rvMahasiswa: RecyclerView
    private lateinit var mahasiswaDao: MahasiswaDao
    private lateinit var adapter: MahasiswaAdapter
    private var selectedId: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etNama = findViewById(R.id.etNama)
        etNim = findViewById(R.id.etNim)
        btnSimpan = findViewById(R.id.btnSimpan)
        btnBatal = findViewById(R.id.btnBatal)
        rvMahasiswa = findViewById(R.id.rvMahasiswa)
        val database = AppDatabase.getDatabase(this)
        mahasiswaDao = database.mahasiswaDao()
        setupRecyclerView()
        setupActions()
        lifecycleScope.launch {
            loadData()
        }
    }
    private fun setupRecyclerView() {
        adapter = MahasiswaAdapter(
            onEdit = { mahasiswa ->
                selectedId = mahasiswa.id
                etNama.setText(mahasiswa.nama)
                etNim.setText(mahasiswa.nim)
                btnSimpan.text = "Update"
                btnBatal.visibility = View.VISIBLE
            },
            onDelete = { mahasiswa ->
                lifecycleScope.launch {
                    mahasiswaDao.delete(mahasiswa)
                    loadData()
                }
            }
        )
        rvMahasiswa.layoutManager = LinearLayoutManager(this)
        rvMahasiswa.adapter = adapter
    }
    private fun setupActions() {
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString().trim()
            val nim = etNim.text.toString().trim()
            if (nama.isEmpty() || nim.isEmpty()) {
                Toast.makeText(
                    this,
                    "Nama dan NIM wajib diisi",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            lifecycleScope.launch {
                val id = selectedId
                if (id == null) {
                    mahasiswaDao.insert(
                        Mahasiswa(nama = nama, nim = nim)
                    )
                } else {
                    mahasiswaDao.update(
                        Mahasiswa(id = id, nama = nama, nim = nim)
                    )
                }
                clearForm()
                loadData()
            }
        }
        btnBatal.setOnClickListener {
            clearForm()
        }
    }
    private suspend fun loadData() {
        val data = mahasiswaDao.getAll()
        adapter.updateData(data)
    }
    private fun clearForm() {
        selectedId = null
        etNama.text.clear()
        etNim.text.clear()
        btnSimpan.text = "Simpan"
        btnBatal.visibility = View.GONE
    }
}