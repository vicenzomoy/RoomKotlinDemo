package edu.uph.m24si2.roomkotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.uph.m24si2.roomkotlindemo.R
import edu.uph.m24si2.roomkotlindemo.model.Product

class        ProductAdapter (
    private val items: List<Product>)
    : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txvProductName: TextView = view.findViewById(R.id.txvProductName)
        val txvPrice: TextView = view.findViewById(R.id.txvPrice)
        val txvidProduct: TextView = view.findViewById(R.id.txvIDProduct)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.txvProductName.text = item.productName
        holder.txvPrice.text = item.price.toString()
        holder.txvidProduct.text = item.idProduct
    }
    override fun getItemCount(): Int = items.size
    fun updateData(newItems: List<Product>) {
        notifyDataSetChanged()
    }
}