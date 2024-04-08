package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class LoginBean(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String // User logged in successfully.
) {
    data class Data(
        @SerializedName("area_id")
        val areaId: Any, // null
        @SerializedName("business_category")
        val businessCategory: Any, // null
        @SerializedName("created_at")
        val createdAt: String, // 2024-04-05 13:27:58
        @SerializedName("designation")
        val designation: String, // SR. SALES MANAGER
        @SerializedName("email")
        val email: String, // tejdeep.singh1989@gmail.com
        @SerializedName("fcm_token")
        val fcmToken: Any, // null
        @SerializedName("firebase_token")
        val firebaseToken: Any, // null
        @SerializedName("id")
        val id: Int, // 43
        @SerializedName("is_active")
        val isActive: Int, // 1
        @SerializedName("last_ip")
        val lastIp: Any, // null
        @SerializedName("last_location")
        val lastLocation: String, // 37.4219983,-122.084
        @SerializedName("last_login")
        val lastLogin: String, // 2024-04-08 12:24:25
        @SerializedName("location_time")
        val locationTime: String, // 2024-04-08 12:49:32
        @SerializedName("name")
        val name: String, // Mr. Tejdeep
        @SerializedName("parent_id")
        val parentId: Any, // null
        @SerializedName("password")
        val password: String, // tejdeep@405
        @SerializedName("phone")
        val phone: String, // 9216755405
        @SerializedName("sm_id")
        val smId: Any, // null
        @SerializedName("token")
        val token: String, // cQsJq80xZkVu
        @SerializedName("updated_at")
        val updatedAt: String, // 2024-04-08 12:50:48
        @SerializedName("user_type")
        val userType: String // Sales manager
    )
}