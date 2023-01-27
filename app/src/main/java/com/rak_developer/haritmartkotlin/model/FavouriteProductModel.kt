package com.rak_developer.haritmartkotlin.model

data class FavouriteProductModel(
    val product_image_path: String,
    val wishlist_id: String,
    val category_name: String,
    val id: String,
    val filter_id: String,
    val name: String,
    val product_image: String,
    val variants_name: String
)


