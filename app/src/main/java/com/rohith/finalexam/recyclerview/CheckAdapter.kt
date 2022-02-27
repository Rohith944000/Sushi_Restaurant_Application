package com.rohith.finalexam.recyclerview

import android.icu.text.NumberFormat
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

class CheckAdapter : RecyclerView.Adapter<CheckAdapter.CheckAdapterViewHolder>() {
    var formatter: NumberFormat = NumberFormat.getCurrencyInstance()


    private val dataSet = mutableListOf<Product>()

    class CheckAdapterViewHolder(
//        private val parentAdapter: DishesAdapter,
        private val containerView: View,

        ) :
        RecyclerView.ViewHolder(containerView) {
        companion object {
            const val EXTRA_KEY_DISH = "Product"
        }

        var product: Product? = null
        val image: ImageView = containerView.findViewById(R.id.check_image)
        val title: TextView = containerView.findViewById(R.id.check_title)
        val description: TextView = containerView.findViewById(R.id.check_description)
        val price: TextView = containerView.findViewById(R.id.check_price)
        val quantity: TextView = containerView.findViewById(R.id.check_quantity)
        val totalPrice: TextView = containerView.findViewById(R.id.check_totalPrice)

        init {


        }

    }

    public fun changeData(dataSet: List<Product>) {
        this.dataSet.clear()
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckAdapterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.checkout_list, parent, false)//always false
        return CheckAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckAdapterViewHolder, position: Int) {
        val currentData = dataSet[position]
        holder.product = currentData
        holder.title.text = currentData.title
        holder.description.text = currentData.description
        holder.price.text = currentData.price.toString()
        holder.image.setImageResource(currentData.image)
        holder.quantity.text = currentData.quantity.toString()

        var totalPrice: Double = currentData.price * currentData.quantity

        var moneyString: String = formatter.format(totalPrice)
        holder.totalPrice.text = moneyString.toString()

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}