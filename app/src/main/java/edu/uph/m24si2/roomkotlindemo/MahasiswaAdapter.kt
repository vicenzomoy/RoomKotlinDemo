package edu.uph.m24si2.roomkotlindemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uph.m24si2.roomkotlindemo.data.Mahasiswa
class MahasiswaAdapter(
    private val onEdit: (Mahasiswa) -> Unit,
    private val onDelete: (Mahasiswa) -> Unit
) : RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>() {
    private val items = mutableListOf<Mahasiswa>()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvNim: TextView = view.findViewById(R.id.tvNim)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnHapus: Button = view.findViewById(R.id.btnHapus)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_mahasiswa, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvNama.text = item.nama
        holder.tvNim.text = "NIM: ${item.nim}"
        holder.btnEdit.setOnClickListener { onEdit(item) }
        holder.btnHapus.setOnClickListener { onDelete(item) }
    }
    override fun getItemCount(): Int = items.size
    fun updateData(newItems: List<Mahasiswa>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}