package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class ConvertedDealerBean(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean, // true
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("lead_products")
        val leadProducts: List<LeadProduct>
    ) {
        data class LeadProduct(
            @SerializedName("created_at")
            val createdAt: String, // 2024-04-01 16:20:10
            @SerializedName("id")
            val id: Int, // 10
            @SerializedName("is_delivered")
            val isDelivered: Int, // 0
            @SerializedName("name")
            val name: String, // 3M SAND FILTER - IAS802F
            @SerializedName("qty")
            val qty: String, // 2.00
            @SerializedName("quote_id")
            val quoteId: Int // 6
        )
    }
}