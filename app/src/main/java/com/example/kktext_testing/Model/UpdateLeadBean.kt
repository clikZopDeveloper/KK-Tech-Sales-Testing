package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class UpdateLeadBean(
    @SerializedName("data")
    val `data`: Any, // null
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String // lead Update successfully.
)