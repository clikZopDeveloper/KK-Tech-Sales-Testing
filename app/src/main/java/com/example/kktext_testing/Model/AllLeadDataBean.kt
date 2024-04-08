package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class AllLeadDataBean(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("address")
        val address: String, // eterter
        @SerializedName("allocated_date")
        val allocatedDate: Any, // null
        @SerializedName("app_city")
        val appCity: Any, // null
        @SerializedName("app_contact")
        val appContact: Any, // null
        @SerializedName("app_doa")
        val appDoa: Any, // null
        @SerializedName("app_dob")
        val appDob: Any, // null
        @SerializedName("app_name")
        val appName: Any, // null
        @SerializedName("architect")
        val architect: String, // Test Architect
        @SerializedName("budget")
        val budget: Any, // null
        @SerializedName("business_category")
        val businessCategory: Any, // null
        @SerializedName("campaign")
        val campaign: Any, // null
        @SerializedName("catg_id")
        val catgId: Int, // 1
        @SerializedName("city")
        val city: String, // Dantewada
        @SerializedName("classification")
        val classification: Any, // null
        @SerializedName("client_address")
        val clientAddress: String, // Moli.Baidwan,Sec-80
        @SerializedName("client_city")
        val clientCity: String, // Mohali
        @SerializedName("client_id")
        val clientId: Int, // 3066
        @SerializedName("client_name")
        val clientName: String, // Mr.H S Waliya
        @SerializedName("client_number")
        val clientNumber: String, // 9814008082
        @SerializedName("conversion_type")
        val conversionType: Any, // null
        @SerializedName("customer_type")
        val customerType: String, // customer
        @SerializedName("dealer_id")
        val dealerId: Any, // null
        @SerializedName("email")
        val email: Any, // null
        @SerializedName("field3")
        val field3: Any, // null
        @SerializedName("field4")
        val field4: Any, // null
        @SerializedName("final_price")
        val finalPrice: Any, // null
        @SerializedName("gst")
        val gst: String, // ertertsdsd
        @SerializedName("id")
        val id: Int, // 2
        @SerializedName("is_allocated")
        val isAllocated: Int, // 0
        @SerializedName("is_interested_allocated")
        val isInterestedAllocated: Int, // 0
        @SerializedName("last_comment")
        val lastComment: String, // cvbcvfgjfj
        @SerializedName("lead_date")
        val leadDate: String, // 2024-04-02 13:44:24
        @SerializedName("mep")
        val mep: String, // Test Architect
        @SerializedName("name")
        val name: Any, // null
        @SerializedName("notes")
        val notes: Any, // null
        @SerializedName("phone")
        val phone: Any, // null
        @SerializedName("plumber")
        val plumber: String, // Test Plumber
        @SerializedName("project_id")
        val projectId: Any, // null
        @SerializedName("projects")
        val projects: Any, // null
        @SerializedName("property_stage")
        val propertyStage: String, // PLANNING
        @SerializedName("quotes_status")
        val quotesStatus: Any, // null
        @SerializedName("remind_date")
        val remindDate: Any, // null
        @SerializedName("remind_time")
        val remindTime: Any, // null
        @SerializedName("size")
        val size: Any, // null
        @SerializedName("source")
        val source: String, // FACEBOOK
        @SerializedName("state")
        val state: String, // Chhattisgarh
        @SerializedName("status")
        val status: String, // NEW LEAD
        @SerializedName("sub_catg_id")
        val subCatgId: Int, // 1
        @SerializedName("type")
        val type: String, // Residential
        @SerializedName("updated_date")
        val updatedDate: Any, // null
        @SerializedName("user_id")
        val userId: Int, // 15
        @SerializedName("whatsapp_no")
        val whatsappNo: Any // null
    )
}