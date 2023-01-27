package com.rak_developer.haritmartkotlin.fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rak_developer.haritmartkotlin.R
import com.rak_developer.haritmartkotlin.adapter.ProductArrivalAdapter
import com.rak_developer.haritmartkotlin.adapter.ProductCategoryAdapter
import com.rak_developer.haritmartkotlin.adapter.SliderImageAdapter
import com.rak_developer.haritmartkotlin.databinding.FragmentHomeBinding
import com.rak_developer.haritmartkotlin.model.ArrivalModel
import com.rak_developer.haritmartkotlin.model.ProductModel
import com.rak_developer.haritmartkotlin.model.SliderModel
import org.json.JSONArray

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val imageLink1: String? =
        "https://npr.brightspotcdn.com/dims4/default/042a5c7/2147483647/strip/true/crop/2000x1000+0+0/resize/880x440!/quality/90/?url=http%3A%2F%2Fnpr-brightspot.s3.amazonaws.com%2F80%2Feb%2F0e57469f4b26a1280c0c5f15dacd%2Fzest-groceries-072122.png"
    private val imageLink2: String? =
        "https://img.particlenews.com/image.php?type=thumbnail_580x000&url=3LC2gM_0jY02Lc900";
    private val imageLink3: String? =
        "https://g.foolcdn.com/image/?url=https%3A%2F%2Fm.foolcdn.com%2Fmedia%2Faffiliates%2Fimages%2Fwoman_grocery_shopping_DJkNWCK.width-793.jpg%3Fwidth%3D793&w=700";
    val imageFruit: String? =
        "https://www.pngarts.com/files/11/Mix-Fruit-Transparent-Image.png";
    val imageProduct1: String? =
        "https://media.istockphoto.com/id/184300090/photo/garden-vegetables.jpg?b=1&s=170667a&w=0&k=20&c=VQd4HLl_H75oGTvNn2gsCRFFNepHekoyT-IVzjWWDGM=";
    val imageProduct2: String? =
        "https://cavitas-oris.com/wp-content/uploads/2019/12/CABBAGE-FRESH-PRODUCE-GROUP-LLC.jpg";
    val imageProduct3: String? =
        "https://www.ranjitmart.com/wp-content/uploads/2020/08/fresh-onion-500x500-1.jpg";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater!!, R.layout.fragment_home, container, false)
        initProductView()
        return binding!!.root
    }

    fun newInstance(): HomeFragment? {
        return HomeFragment()
    }

    fun initProductView() {
        setupSliderData()
        setProductData()
        specialProductArrival()
        newProductArrival()
        trendingProductArrival()
    }

    fun setupSliderData() {
        val sliderModelList: MutableList<SliderModel> = ArrayList<SliderModel>()
        sliderModelList.add(
            SliderModel(
                "", "", "", imageLink1!!, ""
            )
        )
        sliderModelList.add(
            SliderModel(
                "", "", "", imageLink2!!, ""
            )
        )
        sliderModelList.add(
            SliderModel(
                "", "", "", imageLink3!!, ""
            )
        )
        val sliderImageAdapter = SliderImageAdapter(requireContext(), sliderModelList)
        binding!!.pager.adapter = sliderImageAdapter
        binding!!.tabDots.setupWithViewPager(binding?.pager, true)
        autoSlider(sliderImageAdapter)

    }

    fun autoSlider(adapter: SliderImageAdapter) {
        try {
            val handler = Handler()
            var runnable: Runnable? = null
            if (runnable != null) handler.removeCallbacks(runnable)
            runnable = object : Runnable {
                override fun run() {
                    handler.postDelayed(this, 3000)
                    if (binding!!.pager.getCurrentItem() === adapter.getCount() - 1) binding!!.pager.setCurrentItem(
                        0
                    ) else binding!!.pager.setCurrentItem(binding!!.pager.getCurrentItem() + 1)
                }
            }
            runnable.run()
        } catch (e: Exception) {

        }
    }

    fun setProductData() {
        val programmingList = ArrayList<ProductModel>()
        val model = ProductModel(
            "",
            "",
            "Item",
            imageFruit!!,
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
            "",
            "",
            0,
            "",
            JSONArray()
        )
        programmingList.add(
            model
        )
        programmingList.add(
            model
        )
        programmingList.add(
            model
        )
        val adapter = ProductCategoryAdapter(programmingList)
        binding!!.recyclerProductItem.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding!!.recyclerProductItem.setHasFixedSize(true)
        binding!!.recyclerProductItem.adapter = adapter
    }

    fun specialProductArrival() {
        val specialArrivalList = ArrayList<ArrivalModel>()
        val model = ArrivalModel("", "", "Vegetables", "", "", imageProduct1!!, "", "", "", "")
        specialArrivalList.add(model)
        specialArrivalList.add(model)
        specialArrivalList.add(model)
        specialArrivalList.add(model)
        specialArrivalList.add(model)
        val adapter = ProductArrivalAdapter(specialArrivalList)
        binding!!.recyclerSpecialProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding!!.recyclerSpecialProduct.setHasFixedSize(true)
        binding!!.recyclerSpecialProduct.adapter = adapter
    }

    fun newProductArrival() {
        val newArrivalList = ArrayList<ArrivalModel>()
        val model = ArrivalModel("", "", "Cabbage", "", "", imageProduct2!!, "", "", "", "")
        newArrivalList.add(model)
        newArrivalList.add(model)
        newArrivalList.add(model)
        newArrivalList.add(model)
        newArrivalList.add(model)
        val adapter = ProductArrivalAdapter(newArrivalList)
        binding!!.recyclerNewProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding!!.recyclerNewProduct.setHasFixedSize(true)
        binding!!.recyclerNewProduct.adapter = adapter
    }

    fun trendingProductArrival() {
        val trendingArrivalList = ArrayList<ArrivalModel>()
        val model = ArrivalModel("", "", "Onion", "", "", imageProduct3!!, "", "", "", "")
        trendingArrivalList.add(model)
        trendingArrivalList.add(model)
        trendingArrivalList.add(model)
        trendingArrivalList.add(model)
        trendingArrivalList.add(model)
        val adapter = ProductArrivalAdapter(trendingArrivalList)
        binding!!.recyclerTrendingProduct.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding!!.recyclerTrendingProduct.setHasFixedSize(true)
        binding!!.recyclerTrendingProduct.adapter = adapter
    }
}