package com.rak_developer.haritmartkotlin.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.FavouriteListActivityBinding
import com.rak_developer.haritmartkotlin.model.FavouriteProductModel
import com.rak_developer.haritmartkotlin.util.Click
import com.squareup.picasso.Picasso

class FavouriteProductAdapter(private val modelList: List<FavouriteProductModel>) :
    RecyclerView.Adapter<FavouriteProductAdapter.ViewHolder>() {

    private val rs = "₹ "

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: FavouriteListActivityBinding) :
        RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteProductAdapter.ViewHolder {
        val binding = FavouriteListActivityBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)

    }

    // bind the items with each item
    // of the list languageList
    // which than will be
    // shown in recycler view
    // to keep it simple we are
    // not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(modelList[position]) {
                binding.txtAmount.text = rs + "60"
                binding.txtProductOff.text = rs + "80"
                holder.binding.txtProductOff.paintFlags =
                    holder.binding.txtProductOff.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtPercent.text = "10" + "% OFF"
                holder.binding.txtCategory.setText(this.category_name + " " + position)
                holder.binding.txtQuantity.text = "" + position + " KG"
                Picasso.get().load(this.product_image)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.drawable.progress_animation).into(holder.binding.imgProduct)

                holder.binding.cardView.setOnClickListener { v ->
                    Click.preventTwoClick(v)
                }

                holder.binding.lnrRemove.setOnClickListener { v ->
                    Click.preventTwoClick(v)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }


}