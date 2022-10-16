package com.rak_developer.haritmartkotlin.model

data class Data(
    val current_version: String,
    val flash_screen_image_path: String,
    val flash_screen_list: List<Any>,
    val ios_current_version: String,
    val ios_min_version: String,
    val min_version: String
)