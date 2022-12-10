package com.rak_developer.haritmartkotlin.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.databinding.FragmentFavouriteBinding
import com.rak_developer.haritmartkotlin.databinding.FragmentHomeBinding

class FavouriteFragment : Fragment() {

    private var binding: FragmentFavouriteBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_favourite, container, false)
        return binding!!.root
    }

    fun newInstance(): FavouriteFragment? {
        return FavouriteFragment()
    }

}