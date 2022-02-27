package com.rohith.finalexam.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rohith.finalexam.R
import com.rohith.finalexam.db.ProductsTable
import com.rohith.finalexam.entity.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder>() {

    private val dataSet = mutableListOf<Product>()


    class ProductAdapterViewHolder(
//        private val parentAdapter: DishesAdapter,
        private val containerView: View,

        ) :
        RecyclerView.ViewHolder(containerView) {
        companion object {
            const val EXTRA_KEY_DISH = "Product"
        }

        var productsTable: ProductsTable = ProductsTable(containerView.context)
        var product: Product? = null
        val image: ImageView = containerView.findViewById(R.id.listItem_image)
        val title: TextView = containerView.findViewById(R.id.listItem_title)
        val description: TextView = containerView.findViewById(R.id.listItem_description)
        val price: TextView = containerView.findViewById(R.id.listItem_price)
        val quantity: TextView = containerView.findViewById(R.id.listItem_quantity)
        val addButton: Button = containerView.findViewById(R.id.listItem_add)
        val subButton: Button = containerView.findViewById(R.id.listItem_sub)


        init {
            addButton.setOnClickListener {
                var value = quantity.text.toString()
                product?.quantity = value.toInt() + 1
                quantity.text = (value.toInt() + 1).toString()
                productsTable.update(product!!)
            }
            subButton.setOnClickListener {
                var value = quantity.text.toString()
                product?.quantity = value.toInt() - 1
                if (value.toInt() > 0) {
                    quantity.text = (value.toInt() - 1).toString()
                    productsTable.update(product!!)
                }
            }

        }

    }

    public fun changeData(dataSet: List<Product>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_list, parent, false)//always false
        return ProductAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        val currentData = dataSet[position]
        holder.product = currentData
        holder.title.text = currentData.title
        holder.description.text = currentData.description
        holder.price.text = currentData.price.toString()
        holder.image.setImageResource(currentData.image)
        holder.quantity.text = currentData.quantity.toString()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


}