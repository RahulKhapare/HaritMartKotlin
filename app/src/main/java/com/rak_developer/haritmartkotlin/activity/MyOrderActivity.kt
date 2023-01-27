package com.rak_developer.haritmartkotlin.activity

import android.app.ActionBar
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.MyOrderAdapter
import com.rak_developer.haritmartkotlin.adapter.OrderSortAdapter
import com.rak_developer.haritmartkotlin.databinding.ActivityMyOrderBinding
import com.rak_developer.haritmartkotlin.model.MyOrderModel
import com.rak_developer.haritmartkotlin.model.OrderSortModel
import com.rak_developer.haritmartkotlin.util.Click
import com.rak_developer.haritmartkotlin.util.Config
import com.rak_developer.haritmartkotlin.util.Toast
import com.rak_developer.haritmartkotlin.util.WindowBar

class MyOrderActivity : AppCompatActivity(), MyOrderAdapter.onClick, OrderSortAdapter.SortClick {

    val activity = this@MyOrderActivity;
    lateinit var binding: ActivityMyOrderBinding
    var sortDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowBar().setColor(activity)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);
        init()
    }

    fun init() {
        binding.toolbar.title = "My Order"
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        setOrderData()
    }

    fun setOrderData() {
        val orderModelList = ArrayList<MyOrderModel>()
        orderModelList.add(MyOrderModel())
        orderModelList.add(MyOrderModel())
        orderModelList.add(MyOrderModel())
        orderModelList.add(MyOrderModel())
        orderModelList.add(MyOrderModel())

        val adapter = MyOrderAdapter(activity, orderModelList)
        binding!!.recyclerOrderDetailList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding!!.recyclerOrderDetailList.setHasFixedSize(true)
        binding!!.recyclerOrderDetailList.adapter = adapter
    }

    override fun cancelOrder(model: MyOrderModel?, txtStatus: TextView?, txtCancel: TextView?) {
        commentDialog(model!!, txtStatus!!, txtCancel!!)
    }

    private fun commentDialog(
        model: MyOrderModel,
        txtStatus: TextView,
        txtCancelOrder: TextView
    ) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.activity_comment_dialog)
        val etxComment = dialog.findViewById<EditText>(R.id.etxComment)
        val txtCancel = dialog.findViewById<TextView>(R.id.txtCancel)
        val txtSubmit = dialog.findViewById<TextView>(R.id.txtSubmit)
        txtSubmit.setOnClickListener { v ->
            Click.preventTwoClick(v)
            val comment = etxComment.text.toString().trim { it <= ' ' }
            if (!TextUtils.isEmpty(comment)) {
                if (comment.length > 10) {
                    dialog.cancel()
                } else {
                    Toast.showMessage(activity, "Enter minimum 10 character")
                }
            } else {
                Toast.showMessage(activity, "Please enter comment")
            }
        }
        txtCancel.setOnClickListener { v ->
            Click.preventTwoClick(v)
            dialog.cancel()
        }
        dialog.setCancelable(true)
        dialog.show()
        val window = dialog.window
        window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_filter, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        } else if (item.itemId == R.id.action_sort) {
            onSortClick()
        }
        return false
    }

    private fun onSortClick() {
        val sortModelList = ArrayList<OrderSortModel>()
        sortModelList.add(OrderSortModel(0, ""))
        sortModelList.add(OrderSortModel(0, ""))
        sortModelList.add(OrderSortModel(0, ""))
        sortModelList.add(OrderSortModel(0, ""))
        sortDialog = Dialog(this)
        sortDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        sortDialog!!.setContentView(R.layout.activity_order_sort_dialog)
        val recyclerSort: RecyclerView = sortDialog!!.findViewById<RecyclerView>(R.id.recyclerSort)
        recyclerSort.setHasFixedSize(true)
        val linearLayoutManager = LinearLayoutManager(activity)
        recyclerSort.layoutManager = linearLayoutManager
        val adapter = OrderSortAdapter(activity,sortModelList)
        recyclerSort.adapter = adapter
        sortDialog!!.setCancelable(true)
        sortDialog!!.show()
        val window = sortDialog!!.window
        window!!.setLayout(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT)
    }

    override fun onSort(position: Int, sortValue: Int, sortName: String?) {
        Config.SORT_POSITION = position
        sortDialog!!.dismiss()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


}