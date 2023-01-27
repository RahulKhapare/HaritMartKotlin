package com.rak_developer.haritmartkotlin.model

data class SearchModel(
    val id: String,
    val filter_id: String,
    val name: String,
    val slug: String,
    val variants_name: String,
    val product_image: String,
    val price: String,
    val saleprice: String,
    val discount_amount: String,
    val discount: String,
)


