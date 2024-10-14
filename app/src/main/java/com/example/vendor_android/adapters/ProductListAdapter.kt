import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vendor_android.R
import com.google.android.material.materialswitch.MaterialSwitch

data class Product(
    val image: Int, // Replace with actual image URL or resource
    val name: String,
    val price: String,
    val originalPrice: String,
    val status: String,
    val isAvailable: Boolean
)

class ProductListAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.product_image)
        val productName: TextView = itemView.findViewById(R.id.product_name)
        val productPrice: TextView = itemView.findViewById(R.id.product_price)
        val productOriginalPrice: TextView = itemView.findViewById(R.id.product_original_price)
        val productStatus: TextView = itemView.findViewById(R.id.product_status)
        val switchToggle: MaterialSwitch = itemView.findViewById(R.id.switch_toggle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productImage.setImageResource(product.image)
        holder.productName.text = product.name
        holder.productPrice.text = product.price
        holder.productOriginalPrice.text = product.originalPrice
        holder.productStatus.text = product.status
        holder.switchToggle.isChecked = product.isAvailable
    }

    override fun getItemCount(): Int = productList.size
}
