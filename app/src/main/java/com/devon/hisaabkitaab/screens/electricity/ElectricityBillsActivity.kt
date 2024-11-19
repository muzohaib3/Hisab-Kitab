package com.devon.hisaabkitaab.screens.electricity


import android.content.Intent
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.devon.hisaabkitaab.R
import com.devon.hisaabkitaab.adapter.MeterListAdapter
import com.devon.hisaabkitaab.databinding.ActivityElectricityBillsBinding
import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import com.devon.hisaabkitaab.extensions.click
import com.devon.hisaabkitaab.extensions.setToolbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.w3c.dom.Document
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ElectricityBillsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityElectricityBillsBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var meterListAdapter: MeterListAdapter
    var sumOfMeterReading = 0

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

//        showBusLocSpinner()

        binding.btGeneratePDF.click {

        }

    }

    override fun onResume() {
        super.onResume()
        fetchList()
    }

    private fun fetchList()
    {

        if (assets == null) {

        }
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

//    private fun showBusLocSpinner()
//    {
//        val spFilterSpinner = binding.spFilter
//        val items = resources.getStringArray(R.array.months)
//
//        val spFilterSpinnerAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
//        spFilterSpinner.adapter = spFilterSpinnerAdapter
//
//        spFilterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//
//            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
//                println("The value of data index is >> ${id+1}")
//
//
//            }
//            override fun onNothingSelected(parent: AdapterView<*>) {
//
//            }
//        }
//    }

    fun setValueOfSum(sum:Int){
        binding.tvSumMeterCount.text = "$sum"
    }


    private fun generatePDF() {

        val data = listOf(
            Triple("123", "456", "2023-11-20"),
            Triple("789", "1011", "2023-11-21")
        )

    }

}