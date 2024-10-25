package com.devon.hisaabkitaab.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devon.hisaabkitaab.R
import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.databinding.MeterReadingViewBinding
import com.devon.hisaabkitaab.screens.electricity.AddMeterReadingActivity
import com.devon.hisaabkitaab.utils.click

class MeterListAdapter(
    private val context: Context,
    private val list:List<MeterReadingModel>
) : RecyclerView.Adapter<MeterListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = MeterReadingViewBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meter_reading_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data= list[position]
        with(holder)
        {
            binding.tvDate.text = "date : ${data.date}"
            binding.tvReadingNo.text = "meter reading : ${data.meter_reading}"
            binding.tvTotalCount.text = "total unit : ${data.total_no_count}"
            var id  = data.id


            binding.btEditMetrReading.click {
                AddOrEditMeterReading("edit",id)
                println("The id of view is $id")
            }
        }
    }

    override fun getItemCount(): Int = list.size

    private fun AddOrEditMeterReading(screen:String,id:Int){
        val intent = Intent(context, AddMeterReadingActivity::class.java)
        intent.putExtra("edit",screen)
        intent.putExtra("view_id",id)
        context.startActivity(intent)
    }

}