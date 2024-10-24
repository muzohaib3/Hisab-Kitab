package com.devon.hisaabkitaab.screens.electricity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.devon.hisaabkitaab.adapter.MeterListAdapter
import com.devon.hisaabkitaab.databinding.ActivityElectricityBillsBinding
import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import com.devon.hisaabkitaab.utils.click
import com.devon.hisaabkitaab.utils.gotoActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ElectricityBillsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityElectricityBillsBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElectricityBillsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        onClickViews()
    }

    private fun onClickViews(){

        binding.btAddMeterReading.click {
            gotoActivity(AddMeterReadingActivity::class.java)
        }
        fetchList()

    }

    override fun onResume() {
        super.onResume()
        fetchList()
    }

    private fun fetchList()
    {
        viewModel.fetchAllReadings { data->

            runOnUiThread {
                binding.rvMeterReading.apply {
                    adapter = MeterListAdapter(this@ElectricityBillsActivity,data)
                    layoutManager = LinearLayoutManager(this@ElectricityBillsActivity)
                }
            }
        }
    }
}