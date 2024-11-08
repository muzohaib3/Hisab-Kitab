package com.devon.hisaabkitaab.screens.electricity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.devon.hisaabkitaab.R
import com.devon.hisaabkitaab.adapter.MeterListAdapter
import com.devon.hisaabkitaab.databinding.ActivityElectricityBillsBinding
import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import com.devon.hisaabkitaab.extensions.click
import com.devon.hisaabkitaab.extensions.setToolbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ElectricityBillsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityElectricityBillsBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var meterListAdapter: MeterListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElectricityBillsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        setToolbar(this, getString(R.string.electricity_bills))

        onClickViews()
    }

    private fun onClickViews(){

        binding.btAddMeterReading.click {
            val intent = Intent(this, AddMeterReadingActivity::class.java)
            intent.putExtra("add","add")
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchList()
    }

    private fun fetchList()
    {
        viewModel.fetchAllReadings { data->

            meterListAdapter = MeterListAdapter(this@ElectricityBillsActivity,data as MutableList)
            if (binding.rvMeterReading != null) {
                runOnUiThread {
                    binding.rvMeterReading.apply {
                        adapter = meterListAdapter
                        layoutManager = LinearLayoutManager(this@ElectricityBillsActivity)
                    }
                    binding.rvMeterReading.adapter?.notifyDataSetChanged()
                }
            }
            else{
                (binding.rvMeterReading.adapter as MeterListAdapter).updateList(data)
            }
        }
    }

    fun deleteItem(index:Int){
        viewModel.deleteMReading(index)
        fetchList()
    }


}