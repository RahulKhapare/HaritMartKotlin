package com.rak_developer.haritmartkotlin.adapter

import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.activity.MyOrderActivity
import com.rak_developer.haritmartkotlin.activity.TrackOrderActivity
import com.rak_developer.haritmartkotlin.databinding.ActivityOrderListItemBinding
import com.rak_developer.haritmartkotlin.model.MyOrderModel
import com.rak_developer.haritmartkotlin.util.Click
import com.squareup.picasso.Picasso

class MyOrderAdapter(
    private val activity: MyOrderActivity,
    private val modelList: List<MyOrderModel>
) :
    RecyclerView.Adapter<MyOrderAdapter.ViewHolder>() {

    interface onClick {
        fun cancelOrder(model: MyOrderModel?, txtStatus: TextView?, txtCancel: TextView?)
    }

    private val rs = "₹."
    val imageProduct: String? =
        "https://www.ranjitmart.com/wp-content/uploads/2020/08/fresh-onion-500x500-1.jpg";


    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: ActivityOrderListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyOrderAdapter.ViewHolder {
        val binding = ActivityOrderListItemBinding.inflate(
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
//                binding.txtTitle.text = this.title
                binding.txtAmount.text = rs + "30"
                binding.txtProductOff.text = rs + "40"
                binding.txtProductOff.paintFlags =
                    binding.txtProductOff.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtPercent.text = "30" + "% OFF"
                binding.txtCategory.text = "Fresh Onion"
                binding.txtQuantity.text = "1 KG"
                holder.binding.txtOrderStatus.setText("Order Processing")

                Picasso.get().load(imageProduct)
                    .error(R.mipmap.ic_launcher)
                    .placeholder(R.drawable.progress_animation).into(holder.binding.imgProduct)

                holder.binding.txtCancelOrder.setOnClickListener { v ->
                    Click.preventTwoClick(v)
                    (activity).cancelOrder(
                        modelList[position],
                        holder.binding.txtOrderStatus,
                        holder.binding.txtCancelOrder
                    )
                }

                holder.binding.txtTrackOrder.setOnClickListener { v ->
                    Click.preventTwoClick(v)
                    activity.startActivity(Intent(activity, TrackOrderActivity::class.java))
                }
            }
        }

        if (position == 0) {
            setMargins(holder.binding.cardView, 20, 50, 20, 10)
        }
    }

    private fun startActivity(intent: Intent) {

    }

    private fun setMargins(view: View, left: Int, top: Int, right: Int, bottom: Int) {
        if (view.layoutParams is MarginLayoutParams) {
            val p = view.layoutParams as MarginLayoutParams
            p.setMargins(left, top, right, bottom)
            view.requestLayout()
        }
    }

    override fun getItemCount(): Int {
        return modelList.size
    }


}