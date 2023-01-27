package com.rak_developer.haritmartkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.CartProductAdapter
import com.rak_developer.haritmartkotlin.adapter.FavouriteProductAdapter
import com.rak_developer.haritmartkotlin.databinding.FragmentCartBinding
import com.rak_developer.haritmartkotlin.databinding.FragmentHomeBinding
import com.rak_developer.haritmartkotlin.model.CartModel
import com.rak_developer.haritmartkotlin.model.FavouriteProductModel

class CartFragment : Fragment() {

    private var binding: FragmentCartBinding? = null
    private val rs = "₹ "
    val imageProduct: String? =
        "https://cavitas-oris.com/wp-content/uploads/2019/12/CABBAGE-FRESH-PRODUCE-GROUP-LLC.jpg";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_cart, container, false)
        initView()
        return binding!!.root
    }

    fun newInstance(): CartFragment? {
        return CartFragment()
    }

    fun initView() {
        setCartData()
        setSubTotalData()
    }

    fun setCartData() {
        val cartProductModelList = ArrayList<CartModel>()
        val model = CartModel(
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            imageProduct,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        )
        cartProductModelList.add(model)
        val adapter = CartProductAdapter(cartProductModelList)
        binding!!.recyclerViewCard.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding!!.recyclerViewCard.setHasFixedSize(true)
        binding!!.recyclerViewCard.adapter = adapter
    }

    fun setSubTotalData() {
        binding!!.txtSubTotal.text = rs + " 42"
        binding!!.txtTaxCharge.text = rs + " 12"
        binding!!.txtDeliverCharge.text = rs + " 10"
        binding!!.txtCouponDiscount.text = rs + " 20"
        binding!!.txtTotalAMount.text = rs + " 64"
    }
}