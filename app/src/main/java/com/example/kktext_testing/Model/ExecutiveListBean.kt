package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class ExecutiveListBean(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("business_category_id")
        val businessCategoryId: String, // 3
        @SerializedName("coordinator_id")
        val coordinatorId: Int, // 9
        @SerializedName("created_at")
        val createdAt: String, // 2024-04-01 16:02:59
        @SerializedName("id")
        val id: Int, // 15
        @SerializedName("reporting_manager_id")
        val reportingManagerId: Int, // 15
        @SerializedName("updated_at")
        val updatedAt: Any, // null
        @SerializedName("user_id")
        val userId: Int, // 14
        @SerializedName("user_name")
        val userName: String, // Mr Sachin
        @SerializedName("user_type")
        val userType: Any // null
    )
}