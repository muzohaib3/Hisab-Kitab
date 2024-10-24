package com.devon.hisaabkitaab.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devon.hisaabkitaab.R
import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.databinding.MeterReadingViewBinding

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
            binding.tvDate.text = data.date
            binding.tvReadingNo.text = data.meter_reading
            binding.tvTotalCount.text = data.total_no_count
        }
    }
    //
    override fun getItemCount(): Int = list.size
}