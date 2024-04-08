package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class SubProductCatBean(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("sub_category")
        val subCategory: String // ACCESSORIES ITEM
    )
}