package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class GetDealerProductBean(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: String, // 2024-03-29 11:47:08
        @SerializedName("dealer_id")
        val dealerId: Int, // 1
        @SerializedName("id")
        val id: Int, // 1
        @SerializedName("price")
        val price: String, // 4000.00
        @SerializedName("product_id")
        val productId: Int, // 3
        @SerializedName("product_name")
        val productName: String, // VORDOSCH INFINITY WATER HEATER VD 9-15
        @SerializedName("updated_at")
        val updatedAt: String // 2024-03-29 11:55:23
    )
}