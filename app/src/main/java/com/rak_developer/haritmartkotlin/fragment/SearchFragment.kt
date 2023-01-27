package com.rak_developer.haritmartkotlin.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.SearchAdapter
import com.rak_developer.haritmartkotlin.databinding.FragmentSearchBinding
import com.rak_developer.haritmartkotlin.model.SearchModel
import java.util.*

class SearchFragment : Fragment() {

    private var binding: FragmentSearchBinding? = null

    var adapter: SearchAdapter? = null;

    var searchList = ArrayList<SearchModel>()

    val imageProduct: String? =
        "https://m.media-amazon.com/images/I/413Q+GBBZWL.jpg";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_search, container, false)
        initView()
        return binding!!.root
    }

    fun newInstance(): SearchFragment? {
        return SearchFragment()
    }

    fun initView() {
        setupSearchData()
        onSearchView()
    }

    fun setupSearchData() {
        val model = SearchModel(
            "", "", "Item", "", "", imageProduct!!, "", "", "", ""
        )
        searchList.add(
            model
        )
        searchList.add(
            model
        )
        searchList.add(
            model
        )
        searchList.add(
            model
        )
        searchList.add(
            model
        )
        adapter = SearchAdapter(searchList)
        binding!!.recyclerTrendingSearches.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding!!.recyclerTrendingSearches.setHasFixedSize(true)
        binding!!.recyclerTrendingSearches.adapter = adapter

    }

    fun onSearchView() {
        binding!!.etxSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val newText = s.toString()
                if (!TextUtils.isEmpty(newText)) {
                    var newSearchList = ArrayList<SearchModel>()
                    for (model in searchList) {
                        if (model.name.toLowerCase()
                                .contains(newText.lowercase(Locale.getDefault()))
                        ) {
                            newSearchList.add(model)
                        }
                    }
                    Log.e("TAG", "onTextChanged: " + newSearchList.size)
                    adapter = SearchAdapter(newSearchList)
                    binding!!.recyclerTrendingSearches.adapter = adapter
                    adapter!!.notifyDataSetChanged()
                } else {
                    adapter = SearchAdapter(searchList)
                    binding!!.recyclerTrendingSearches.adapter = adapter
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }
}