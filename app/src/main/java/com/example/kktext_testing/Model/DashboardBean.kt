package com.example.kktext_testing.Model


import com.google.gson.annotations.SerializedName

data class DashboardBean(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("0")
    val x0: String
) {
    data class Data(
        @SerializedName("booked")
        val booked: String,
        @SerializedName("call_scheduled")
        val callScheduled: String,
        @SerializedName("cancelled")
        val cancelled: String,
        @SerializedName("completed")
        val completed: String,
        @SerializedName("converted_leads")
        val convertedLeads: String,
        @SerializedName("executive_count")
        val executiveCount: Int,
        @SerializedName("followups")
        val followups: List<DashboardBean.Data.Followup>,
        @SerializedName("interested_leads")
        val interestedLeads: String,
        @SerializedName("lost_leads")
        val lostLeads: String,
        @SerializedName("monthly_sale")
        val monthlySale: Double,
        @SerializedName("monthly_sale_executive")
        val monthlySaleExecutive: Double,
        @SerializedName("new_leads")
        val newLeads: String,
        @SerializedName("Partial")
        val partial: String,
        @SerializedName("pending_leads")
        val pendingLeads: String,
        @SerializedName("processing_leads")
        val processingLeads: String,
        @SerializedName("sm_newLeads")
        val smNewLeads: String,
        @SerializedName("total_leads")
        val totalLeads: Int,
        @SerializedName("visit_done")
        val visitDone: String,
        @SerializedName("visit_scheduled")
        val visitScheduled: String,
        @SerializedName("year_sale_executive")
        val yearSaleExecutive: Double,
        @SerializedName("yearly_sale")
        val yearlySale: Double
    ) {
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