package com.rak_developer.haritmartkotlin.model

import org.json.JSONArray

data class MyOrderModel(
    var id: String? = null,
    var order_number: String? = null,
    var ordered_date: String? = null,
    var ordered_amount: String? = null,
    var name: String? = null,
    var email: String? = null,
    var phone: String? = null,
    var company_name: String? = null,
    var gst_number: String? = null,
    var productItemList: JSONArray? = null,
    var grand_total: String? = null,
    var tax_name: String? = null,
    var tax_amount: String? = null,
    var delivery_name: String? = null,
    var delivery_amount: String? = null,
    var coupon_code_id: String? = null,
    var coupon_code: String? = null,
    var coupon_discount_amount: String? = null,
    var item_total: String? = null,
    var shiptodifferetadd: String? = null,
    var bill_full_name: String? = null,
    var bill_phone: String? = null,
    var bill_email: String? = null,
    var bill_phone2: String? = null,
    var bill_address_type: String? = null,
    var bill_address: String? = null,
    var bill_address2: String? = null,
    var bill_landmark: String? = null,
    var bill_locality: String? = null,
    var bill_country: String? = null,
    var bill_country_id: String? = null,
    var bill_state: String? = null,
    var bill_state_id: String? = null,
    var bill_city: String? = null,
    var bill_city_id: String? = null,
    var bill_pincode: String? = null,
    var ship_full_name: String? = null,
    var ship_phone: String? = null,
    var ship_email: String? = null,
    var ship_phone2: String? = null,
    var ship_address_type: String? = null,
    var ship_address: String? = null,
    var ship_address2: String? = null,
    var ship_landmark: String? = null,
    var ship_locality: String? = null,
    var ship_country: String? = null,
    var ship_country_id: String? = null,
    var ship_state: String? = null,
    var ship_state_id: String? = null,
    var ship_city: String? = null,
    var ship_city_id: String? = null,
    var ship_pincode: String? = null,
    var payment_id: String? = null,
    var payment_name: String? = null,
    var payment_info: String? = null,
    var payment_status: String? = null,
    var order_status: String? = null,
    var order_status_comment: String? = null,
    var pdf_url: String? = null
)


