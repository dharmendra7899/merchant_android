package com.example.vendor_android.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R


class ProductAdapter(
    private val productList: List<Pair<String, String>>,
    private val itemClick: (String) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item_list, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val (title, subtitle) = productList[position]
        holder.productTitle.text = title
        holder.productSubtitle.text = subtitle

        holder.itemView.setOnClickListener {
            itemClick(title)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productTitle: TextView = itemView.findViewById(R.id.product_title)
        val productSubtitle: TextView = itemView.findViewById(R.id.product_subtitle)
    }
}
