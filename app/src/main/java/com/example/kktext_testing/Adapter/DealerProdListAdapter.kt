package com.example.kktext_testing.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.kktext_testing.Model.GetDealerProductBean
import com.example.kktext_testing.Model.MultipleProListBean
import com.example.kktext_testing.R
import com.example.kktext_testing.Utills.RvDealerClickListner
import com.example.kktext_testing.Utills.RvStatusClickListner
import com.example.kktext_testing.databinding.ItemDealerBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.stpl.antimatter.Utils.ApiContants


class DealerProdListAdapter(var context: Activity, var list: List<GetDealerProductBean.Data>, var rvClickListner: RvDealerClickListner) : RecyclerView.Adapter<DealerProdListAdapter.MyViewHolder>(){

   // private val data = mutableListOf<Int>()

    val data: MutableList<MultipleProListBean> = ArrayList()
    inner class MyViewHolder(val binding: ItemDealerBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // infalte the item Layout
        val binding =
            ItemDealerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //   val v = LayoutInflater.from(parent.context).inflate(R.layout.item_project_list, parent, false)
        return MyViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


   /*     holder.tvAdd.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(20f))
        holder.tvQtyAdd.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(100f))
        holder.tvQtyMinus.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(100f))
        holder.tvQty.background = RoundView(Color.TRANSPARENT, RoundView.getRadius(20f), true, R.color.orange)
        holder.tvOff.background = RoundView(context.resources.getColor(R.color.orange), RoundView.getRadius(20f))
      */

        holder.setIsRecyclable(false)
        holder.binding.apply {
            tvName.text = list[position].productName.toString()
            tvMobileNo.text =ApiContants.currency+ list[position].price.toString()
            tvDate.text = list[position].createdAt.toString()
            CheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    Toast.makeText(context,"1",Toast.LENGTH_SHORT).show()

                    openDialog(position)
                  //  data.add(list[position].productId)

                } else {
                    Toast.makeText(context,"2",Toast.LENGTH_SHORT).show()
                    val multipleList = MultipleProListBean(
                        list[position].productId.toString(),
                        "binding.editQty.text.toString()"
                    )
                    data.remove(multipleList)
                }

                Log.d("zxczxc", Gson().toJson(data))
                rvClickListner.clickPos(data, list[position].productId)
            }
        }

       // holder.ivImage.setImageDrawable(context.resources.getDrawable(list[position].drawableId))

      /*  if ("Retailer"=="Retailer"){
      //      holder.itemView.visibility=View.GONE
        }*/

       /* holder.itemView.setOnClickListener {
            rvClickListner.clickPos(list[position].name,list[position].id)
        }*/
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun openDialog(position: Int) {
        val builder = AlertDialog.Builder(context,R.style.CustomAlertDialog)
            .create()
        val view = context.layoutInflater.inflate(R.layout.dialog_reamrk,null)
        val  btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        val  editQty = view.findViewById<TextInputEditText>(R.id.editQty)
        val  ivClose = view.findViewById<ImageView>(R.id.ivClose)

        builder.setView(view)
        ivClose.setOnClickListener {
            builder.dismiss()
        }
        btnSubmit.setOnClickListener {
            builder.dismiss()

            val multipleList = MultipleProListBean(
                list[position].productId.toString(),
                editQty.text.toString()
            )
            data.add(multipleList)
        }

        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }

}