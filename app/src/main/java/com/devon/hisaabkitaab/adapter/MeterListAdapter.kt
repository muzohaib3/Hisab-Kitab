package com.devon.hisaabkitaab.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.devon.hisaabkitaab.R
import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.databinding.MeterReadingViewBinding
import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import com.devon.hisaabkitaab.screens.electricity.AddMeterReadingActivity
import com.devon.hisaabkitaab.extensions.click
import com.devon.hisaabkitaab.extensions.makeGone
import com.devon.hisaabkitaab.extensions.makeVisible
import com.devon.hisaabkitaab.screens.electricity.ElectricityBillsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MeterListAdapter(
    private val context: Context,
    private val list:MutableList<MeterReadingModel>
) : RecyclerView.Adapter<MeterListAdapter.ViewHolder>() {

    var itemFlag = 0
    var sum = 0

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
            binding.llElectricityBills.setOnLongClickListener { it->

                if(itemFlag == 0) {

                    itemFlag = 1
                    it.setBackgroundResource(R.drawable.yellow_rounded_bg)
                    binding.btDeleteMetrReading.makeVisible()

                    val index = data.id
                    var totalMeterCount = data.total_no_count
                    sumOfMeterReading(totalMeterCount.toInt())

                    when(index)
                    {
                        0->{
                            binding.btDeleteMetrReading.click {
                                println("it comes here >> 2")
                                Toast.makeText(context, "Item can't be deleted", Toast.LENGTH_SHORT).show()
                            }
                        }
                        null->{
                            binding.btDeleteMetrReading.click {
                                println("it comes here >> 3")
                                Toast.makeText(context, "Item can't be deleted", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else->{
                            binding.btDeleteMetrReading.click {
                                println("it comes here >> 4")
                                (context as ElectricityBillsActivity).deleteItem(data.id)
                            }
                        }
                    }

                }
                else{
                    itemFlag = 0
                    it.setBackgroundResource(R.drawable.blue_squared_rounded_bg)
                    binding.btDeleteMetrReading.makeGone()
                }
                true
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

    fun updateList(newList: List<MeterReadingModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    fun sumOfMeterReading(totalCount:Int):Int{

        var sum = 0
        sum += totalCount
        return sum

    }

}