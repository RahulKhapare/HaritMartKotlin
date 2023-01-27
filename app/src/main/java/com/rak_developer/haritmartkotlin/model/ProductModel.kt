package com.rak_developer.haritmartkotlin.model

import org.json.JSONArray

data class ProductModel(
    val id: String,
    val parent_id: String,
    val name: String,
    val image: String,
    val main_parent_id: String,
    val filter_id: String,
    val category_name: String,
    val product_image: String,
    val is_wishlisted: String,
    val price: String,
    val saleprice: String,
    val slug: String,
    val discount_amount: String,
    val discount: String,
    val variants_name: String,
    val externalFilterId: String,
    val externalFilterName: String,
    val position: Int,
    val jsonArrayData: String,
    val filter_option: JSONArray
)


