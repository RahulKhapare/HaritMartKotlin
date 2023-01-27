package com.rak_developer.haritmartkotlin.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.FavouriteProductAdapter
import com.rak_developer.haritmartkotlin.databinding.FragmentFavouriteBinding
import com.rak_developer.haritmartkotlin.model.FavouriteProductModel

class FavouriteFragment : Fragment() {

    private var binding: FragmentFavouriteBinding? = null

    val imageProduct: String? =
        "https://www.ranjitmart.com/wp-content/uploads/2020/08/fresh-onion-500x500-1.jpg";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_favourite, container, false)
        initView()
        return binding!!.root
    }

    fun newInstance(): FavouriteFragment? {
        return FavouriteFragment()
    }

    fun initView() {
        setFavouriteData()
    }

    fun setFavouriteData() {
        val favouriteProductModelList = ArrayList<FavouriteProductModel>()
        val model = FavouriteProductModel(imageProduct!!, "", "Onion", "", "", "", imageProduct!!, "")
        favouriteProductModelList.add(model)
        favouriteProductModelList.add(model)
        favouriteProductModelList.add(model)
        favouriteProductModelList.add(model)
        favouriteProductModelList.add(model)
        val adapter = FavouriteProductAdapter(favouriteProductModelList)
        binding!!.recyclerFavourite.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding!!.recyclerFavourite.setHasFixedSize(true)
        binding!!.recyclerFavourite.adapter = adapter
    }

}