package com.rak_developer.haritmartkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.ActivityProductListBinding
import com.rak_developer.haritmartkotlin.databinding.ProductArrivallActivityBinding
import com.rak_developer.haritmartkotlin.model.ArrivalModel
import com.rak_developer.haritmartkotlin.util.Click
import com.squareup.picasso.Picasso

class ProductArrivalAdapter(private val modelList: List<ArrivalModel>) :
    RecyclerView.Adapter<ProductArrivalAdapter.ViewHolder>() {

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: ProductArrivallActivityBinding) :
        RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductArrivalAdapter.ViewHolder {
        val binding = ProductArrivallActivityBinding.inflate(
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
                binding.txtName.text = this.name + " " + position
                Picasso.get().load(this.product_image)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.drawable.progress_animation).into(holder.binding.imgVeg)
                holder.binding.imgAction.setOnClickListener { v ->
                    Click.preventTwoClick(v)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }


}