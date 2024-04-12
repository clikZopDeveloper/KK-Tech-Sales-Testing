package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class dfdfd(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean, // false
    @SerializedName("msg")
    val msg: String, // Data found
    @SerializedName("0")
    val x0: String // leads
) {
    data class Data(
        @SerializedName("booked")
        val booked: String, // 0
        @SerializedName("call_scheduled")
        val callScheduled: String, // 87
        @SerializedName("cancelled")
        val cancelled: String, // 0
        @SerializedName("completed")
        val completed: String, // 4
        @SerializedName("converted_leads")
        val convertedLeads: String, // 3
        @SerializedName("executive_count")
        val executiveCount: Int, // 0
        @SerializedName("followups")
        val followups: List<Followup>,
        @SerializedName("interested_leads")
        val interestedLeads: String, // 0
        @SerializedName("lost_leads")
        val lostLeads: String, // 2
        @SerializedName("monthly_sale")
        val monthlySale: Double, // 122928.66
        @SerializedName("monthly_sale_executive")
        val monthlySaleExecutive: Double, // 122928.66
        @SerializedName("new_leads")
        val newLeads: String, // 5
        @SerializedName("Partial")
        val partial: String, // 7
        @SerializedName("pending_leads")
        val pendingLeads: String, // 0
        @SerializedName("processing_leads")
        val processingLeads: String, // 7
        @SerializedName("sm_newLeads")
        val smNewLeads: String, // 0
        @SerializedName("total_leads")
        val totalLeads: Int, // 281
        @SerializedName("visit_done")
        val visitDone: String, // 57
        @SerializedName("visit_scheduled")
        val visitScheduled: String, // 104
        @SerializedName("year_sale_executive")
        val yearSaleExecutive: Int, // 1061668
        @SerializedName("yearly_sale")
        val yearlySale: Int // 1061668
    )
    {
        data class Followup(
            @SerializedName("id")
            val id: Int, // 2764
            @SerializedName("name")
            val name: String, // MR. Rommy Bansal Advocate
            @SerializedName("number")
            val number: String, // 8896510001
            @SerializedName("remind_date")
            val remindDate: String, // 2024-2-29
            @SerializedName("remind_time")
            val remindTime: String,
            @SerializedName("status")
            val status: String // VISIT SCHEDULED
        )
    }
}