package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class BusinessCatBean(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("created_at")
        val createdAt: String, // 2024-03-31 16:29:49
        @SerializedName("id")
        val id: Int, // 3
        @SerializedName("name")
        val name: String // OSTENDORF & MULTITUBO
    )
}