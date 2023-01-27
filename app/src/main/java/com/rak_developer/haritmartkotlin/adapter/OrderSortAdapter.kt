package com.rak_developer.haritmartkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rak_developer.haritmartkotlin.activity.MyOrderActivity
import com.rak_developer.haritmartkotlin.databinding.ActivityOrderSortListBinding
import com.rak_developer.haritmartkotlin.model.OrderSortModel
import com.rak_developer.haritmartkotlin.util.Click
import com.rak_developer.haritmartkotlin.util.Config

class OrderSortAdapter(
    private val activity: MyOrderActivity,
    private val modelList: List<OrderSortModel>
) :
    RecyclerView.Adapter<OrderSortAdapter.ViewHolder>() {

    private var lastCheckPosition = 0

    interface SortClick {
        fun onSort(position: Int, sortValue: Int, sortName: String?)
    }

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: ActivityOrderSortListBinding) :
        RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OrderSortAdapter.ViewHolder {
        val binding = ActivityOrderSortListBinding.inflate(
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

                lastCheckPosition = Config.SORT_POSITION
                binding.radioSort.text = "Filter " + position
                binding.radioSort.isChecked = position == lastCheckPosition
                binding.radioSort.setOnClickListener { v ->
                    Click.preventTwoClick(v)
                    lastCheckPosition = holder.adapterPosition
                    notifyItemRangeChanged(0, modelList.size)
                    (activity).onSort(position, modelList[position].key, value)
                }


                if (position == 0) {
                    binding.txtTitle.visibility = View.VISIBLE
                    binding.txtTitle.text = "Order Type"
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }


}