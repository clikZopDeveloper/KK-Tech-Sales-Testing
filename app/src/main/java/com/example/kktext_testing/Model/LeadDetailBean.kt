package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class LeadDetailBean(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String
) {
    data class Data(
        @SerializedName("lead_comments")
        val leadComments: List<LeadComment>,
        @SerializedName("lead_data")
        val leadData: LeadData,
        @SerializedName("lead_products")
        val leadProducts: List<LeadProduct>,
        @SerializedName("quote_product")
        val quoteProduct: List<QuoteProduct>
    ) {
        data class LeadComment(
            @SerializedName("comment")
            val comment: String,
            @SerializedName("created_date")
            val createdDate: String, // 2024-04-02 16:05:11
            @SerializedName("id")
            val id: Int, // 3
            @SerializedName("lead_id")
            val leadId: Int, // 9
            @SerializedName("remind_date")
            val remindDate: String,
            @SerializedName("remind_time")
            val remindTime: String,
            @SerializedName("status")
            val status: String, // CONVERTED
            @SerializedName("user_id")
            val userId: Int // 15
        )

        data class LeadData(
            @SerializedName("address")
            val address: String, // yh
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
            val businessCategory: String, // ROOFTOP & DRAINAGE
            @SerializedName("campaign")
            val campaign: Any, // null
            @SerializedName("category")
            val category: String, // VILLA
            @SerializedName("catg_id")
            val catgId: Int, // 1
            @SerializedName("city")
            val city: String, // Bhojpur
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
            val conversionType: String,
            @SerializedName("customer_type")
            val customerType: String, // customer
            @SerializedName("dealer_id")
            val dealerId: Any, // null
            @SerializedName("email")
            val email: String, // null
            @SerializedName("field3")
            val field3: Any, // null
            @SerializedName("field4")
            val field4: Any, // null
            @SerializedName("final_price")
            val finalPrice: Any, // null
            @SerializedName("gst")
            val gst: String, // null
            @SerializedName("id")
            val id: Int, // 9
            @SerializedName("is_allocated")
            val isAllocated: Int, // 0
            @SerializedName("is_interested_allocated")
            val isInterestedAllocated: Int, // 0
            @SerializedName("last_comment")
            val lastComment: Any, // null
            @SerializedName("lead_date")
            val leadDate: String, // 2024-04-04 16:31:01
            @SerializedName("mep")
            val mep: String, // Test Architect
            @SerializedName("name")
            val name: String, // null
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
            val quotesStatus: Int, // 0
            @SerializedName("remind_date")
            val remindDate: Any, // null
            @SerializedName("remind_time")
            val remindTime: Any, // null
            @SerializedName("size")
            val size: Any, // null
            @SerializedName("source")
            val source: String, // FACEBOOK
            @SerializedName("state")
            val state: String, // Bihar
            @SerializedName("status")
            val status: String, // CONVERTED
            @SerializedName("sub_category")
            val subCategory: String, // 500 SQYDS
            @SerializedName("sub_catg_id")
            val subCatgId: Int, // 1
            @SerializedName("type")
            val type: String, // Residential
            @SerializedName("updated_date")
            val updatedDate: String, // 2024-04-04 16:49:31
            @SerializedName("user_id")
            val userId: Int, // 33
            @SerializedName("whatsapp_no")
            val whatsappNo: String // null
        )

        data class LeadProduct(
            @SerializedName("created_at")
            val createdAt: String, // 2024-04-04 16:31:01
            @SerializedName("dealer_id")
            val dealerId: Int, // 0
            @SerializedName("id")
            val id: Int, // 26
            @SerializedName("is_dealer")
            val isDealer: Int, // 0
            @SerializedName("is_delivered")
            val isDelivered: Int, // 0
            @SerializedName("lead_id")
            val leadId: Int, // 9
            @SerializedName("product_id")
            val productId: Int, // 1
            @SerializedName("product_name")
            val productName: String, // BRADFORD WHITE CEA80 45KW 415V 3 PH
            @SerializedName("product_price")
            val productPrice: String, // 2130000.00
            @SerializedName("qty")
            val qty: String, // 1.00
            @SerializedName("updated_at")
            val updatedAt: Any // null
        )

        data class QuoteProduct(
            @SerializedName("created_at")
            val createdAt: String, // 2024-04-04 16:47:38
            @SerializedName("dealer_id")
            val dealerId: Int, // 3
            @SerializedName("dealer_name")
            val dealerName: String, // BHAGWATI BUILDING STORE
            @SerializedName("id")
            val id: Int, // 24
            @SerializedName("is_dealer")
            val isDealer: Int, // 1
            @SerializedName("is_delivered")
            val isDelivered: Int, // 0
            @SerializedName("name")
            val name: String, // BRADFORD WHITE CEA80 45KW 415V 3 PH
            @SerializedName("qty")
            val qty: String, // 1.00
              @SerializedName("price")
            val price: String, // 1.00
            @SerializedName("quote_id")
            val quoteId: Int // 7
        )
    }
}