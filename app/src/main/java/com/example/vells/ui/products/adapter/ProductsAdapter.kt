package com.example.vells.ui.products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vells.R
import com.example.vells.data.model.ProductPOJO
import com.example.vells.databinding.ItemProductsRecyclerBinding
import kotlinx.android.synthetic.main.item_products_recycler.view.*

class ProductsAdapter(
    private val products: List<ProductPOJO>,
    private val onClick: (ProductPOJO) -> Unit,
    private var optionsMenuClickListener: OptionsMenuClickListener
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    // create an interface for onClickListener
    // so that we can handle data most effectively in MainActivity.kt
    interface OptionsMenuClickListener {
        fun onOptionsMenuClicked(position: Int)
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val binding = ItemProductsRecyclerBinding.bind(view)

        fun bind(product: ProductPOJO) {
            binding.tvProductName.text = product.name
            binding.tvProductDescription.text = product.description
            binding.tvProductPrice.text = "$" + product.price.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(layoutInflater.inflate(R.layout.item_products_recycler, parent, false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            onClick(product)
        }
        holder.itemView.moreIcon.setOnClickListener {
            optionsMenuClickListener.onOptionsMenuClicked(position)
        }
    }

    override fun getItemCount(): Int = products.size
}