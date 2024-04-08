package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class DealerBean(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("address")
        val address: String, // address
        @SerializedName("adhar_number")
        val adharNumber: String, // ADH
        @SerializedName("city")
        val city: String,
        @SerializedName("created_at")
        val createdAt: String, // 2024-03-2818:54:02
        @SerializedName("email")
        val email: String, // office@kktechecoproducts.com
        @SerializedName("gst")
        val gst: String, // GST123456
        @SerializedName("id")
        val id: Int, // 1
        @SerializedName("name")
        val name: String, // Dealer
        @SerializedName("pan_number")
        val panNumber: String, // PAN9685745987
        @SerializedName("phone")
        val phone: String, // 8596859874
        @SerializedName("pincode")
        val pincode: String, // 24425
        @SerializedName("state")
        val state: String,
        @SerializedName("team_ids")
        val teamIds: String // 33,36,58,59,37,10
    )
}