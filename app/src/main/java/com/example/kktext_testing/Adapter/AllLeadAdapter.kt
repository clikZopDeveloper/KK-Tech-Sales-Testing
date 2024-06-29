package com.example.kktext_testing.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kktext_testing.Activity.EditLeadActivity
import com.example.kktext_testing.Activity.UpdateLeadActivity
import com.example.kktext_testing.Model.AllLeadDataBean
import com.example.kktext_testing.Model.MEPBean
import com.example.kktext_testing.R
import com.example.kktext_testing.Utills.RvStatusClickListner


class AllLeadAdapter(var context: Activity, var list: List<AllLeadDataBean.Data>,var leadStatus:String?, var rvClickListner: RvStatusClickListner) : RecyclerView.Adapter<AllLeadAdapter.MyViewHolder>(),
    Filterable {
    var mFilteredList: MutableList<AllLeadDataBean.Data> = list as MutableList<AllLeadDataBean.Data>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder { // infalte the item Layout
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_allleaddata, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setIsRecyclable(false)

   /*     holder.tvAdd.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(20f))
        holder.tvQtyAdd.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(100f))
        holder.tvQtyMinus.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(100f))
        holder.tvQty.background = RoundView(Color.TRANSPARENT, RoundView.getRadius(20f), true, R.color.orange)
        holder.tvOff.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(20f))
        holder.tvAdd.visibility = View.VISIBLE*/

        holder.tvID.text= mFilteredList[position].id.toString()
        holder.tvSource.text= mFilteredList[position].source
      //  holder.tvPlumber.text= "Plumber : "+list[position].plumber
        holder.tvArchitect.text= mFilteredList[position].architect
        holder.tvClient.text= mFilteredList[position].clientName
        holder.tvClientNo.text= mFilteredList[position].clientNumber.toString()
        holder.tvLeadDate.text=mFilteredList[position].leadDate
        holder.tvmep.text=mFilteredList[position].mep
        holder.ivUpdate.setOnClickListener {
          context.startActivity(Intent(context,UpdateLeadActivity::class.java)
               .putExtra("leadID",mFilteredList[position].id.toString())
               .putExtra("customerType",mFilteredList[position].customerType.toString())
               .putExtra("leadStatus",leadStatus.toString()))
        }

        holder.ivEditLead.setOnClickListener {
            context.startActivityForResult(Intent(context, EditLeadActivity::class.java)
                .putExtra("leadID",mFilteredList[position].id.toString())
                .putExtra("leadStatus",leadStatus.toString())
                .putExtra("source", mFilteredList[position].source)
                .putExtra("architect",mFilteredList[position].architect)
                .putExtra("client",mFilteredList[position].clientName)
                .putExtra("date",mFilteredList[position].leadDate)
                .putExtra("propertyStage",mFilteredList[position].propertyStage)
                .putExtra("gst",mFilteredList[position].gst?.toString())
                .putExtra("state",mFilteredList[position].state)
                .putExtra("city",mFilteredList[position].city)
                .putExtra("mep",mFilteredList[position].mep?.toString())
                .putExtra("catID",mFilteredList[position].catgId.toString())
                .putExtra("subCatID",mFilteredList[position].subCatgId.toString())
                .putExtra("way","AllLead"),101
            )
        }

       // holder.ivImage.setImageDrawable(context.resources.getDrawable(list[position].drawableId))

      /*  if ("Retailer"=="Retailer"){
      //      holder.itemView.visibility=View.GONE
        }*/

        holder.itemView.setOnClickListener {
            rvClickListner.clickPos(mFilteredList[position].status,mFilteredList[position].id)
        }

    }

    override fun getItemCount(): Int {
        return mFilteredList.size
    }

    inner class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val tvID: TextView = itemview.findViewById(R.id.tvID)
       val tvSource: TextView = itemview.findViewById(R.id.tvSource)
       val tvPlumber: TextView = itemview.findViewById(R.id.tvPlumber)
       val tvmep: TextView = itemview.findViewById(R.id.tvmep)
       val ivEditLead: TextView = itemview.findViewById(R.id.ivEditLead)
       val tvArchitect: TextView = itemview.findViewById(R.id.tvArchitect)
       val tvClient: TextView = itemview.findViewById(R.id.tvClient)
       val tvLeadDate: TextView = itemview.findViewById(R.id.tvLeadDate)
       val tvClientNo: TextView = itemview.findViewById(R.id.tvClientNo)
       val ivUpdate: ImageView = itemview.findViewById(R.id.ivUpdate)
    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    mFilteredList = list as MutableList<AllLeadDataBean.Data>
                } else {
                    val filteredList = ArrayList<AllLeadDataBean.Data>()
                    for (serviceBean in list) {
                        if (serviceBean.id.toString().toLowerCase().contains(charString.toLowerCase())
                            ||serviceBean.source.toString().toLowerCase().contains(charString.toLowerCase())
                            ||serviceBean.architect.toString().toLowerCase().contains(charString.toLowerCase())
                            ||serviceBean.clientName.toString().toLowerCase().contains(charString.toLowerCase())
                            ||serviceBean.clientNumber.toString().toLowerCase().contains(charString.toLowerCase())
                        ) {
                            filteredList.add(serviceBean)
                        }
                    }
                    mFilteredList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = mFilteredList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                mFilteredList = filterResults.values as ArrayList<AllLeadDataBean.Data>
                android.os.Handler().postDelayed(Runnable { notifyDataSetChanged() }, 200)
            }
        }
    }
}