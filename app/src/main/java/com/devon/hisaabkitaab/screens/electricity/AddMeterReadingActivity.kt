package com.devon.hisaabkitaab.screens.electricity

import android.R.attr.y
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.databinding.ActivityAddMeterReadingBinding
import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import com.devon.hisaabkitaab.utils.click
import com.devon.hisaabkitaab.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar


class AddMeterReadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMeterReadingBinding
    private val viewModel: MainViewModel by viewModel()
    private var exception = Exception()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMeterReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()

        supportActionBar?.hide()
    }

    private fun initViews(){

        if (intent != null)
        {
            var data = intent.extras?.getString("edit")
            var add = intent.extras?.getString("add")
            var id = intent.extras?.getInt("view_id")!!
            println("data is $data and id id $id")

            when
            {
                data == "edit" ->{
                    binding.btAddMeterUnits.text = "Edit Reading"
                    binding.btAddMeterUnits.click {
                        editReadingProcess(id)
                    }
                }

                add == "add" ->{
                    binding.btAddMeterUnits.click {
                        insertMeterReading()
                    }
                }
            }
        }
    }

    private fun insertMeterReading() {
        kotlin.runCatching {

            try {
                var readingData = binding.etMeterReading.text.toString()
                var date = binding.etDate.text.toString()
                var totalCount = binding.etUnitCount.text.toString()

                when
                {
                    readingData.isNullOrEmpty()->{
                        toast("readingData missing")
                    }
                    date.isNullOrEmpty()->{
                        toast("date missing")
                    }
                    totalCount.isNullOrEmpty()->{
                        toast("totalCount missing")
                    }
                    else->{
                        val meterReading = MeterReadingModel(date,readingData,totalCount)
                        viewModel.insertReading(meterReading)

                        finish()
                    }
                }

            }catch (e:Exception)
            {
                println("The exception => ${exception.message}")
            }


        }.onFailure {

            println("The exception => ${exception.message}")
        }

    }

    private fun editReadingProcess(id:Int){

        var meterReading = binding.etMeterReading.text.toString()
        var date = binding.etDate.text.toString()
        var totalUnit = binding.etUnitCount.text.toString()

        when
        {
            meterReading.isNullOrEmpty()->{
                toast("meterReading missing")
            }
            date.isNullOrEmpty()->{
                toast("date missing")
            }
            totalUnit.isNullOrEmpty()->{
                toast("totalUnit missing")
            }
            else->{
                editMeterReading(id,meterReading,date,totalUnit)
                toast("updated")
                finish()

            }
        }
    }

    private fun editMeterReading(id:Int,date:String,noCount:String, meterData:String){
        viewModel.updateMeterReading(date,meterData,noCount,id)
    }

//    private fun datePicker()
//    {
//        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view2, thisYear, thisMonth, thisDay ->
//            // Display Selected date in textbox
//
//            var date: Calendar = Calendar.getInstance()
//            var thisAYear = date.get(Calendar.YEAR).toInt()
//            var thisAMonth = date.get(Calendar.MONTH).toInt()
//            var thisADay = date.get(Calendar.DAY_OF_MONTH).toInt()
//
//
//            thisAMonth = thisMonth + 1
//            thisADay = thisDay
//            thisAYear = thisYear
//
//            binding.datePickerDate.setText("Date: " + thisAMonth + "/" + thisDay + "/" + thisYear)
//            val newDate:Calendar =Calendar.getInstance()
//            newDate.set(thisYear, thisMonth, thisDay)
//            mh.entryDate = newDate.timeInMillis // setting new date
//        }, thisAYear, thisAMonth, thisADay)
//        dpd.show()
//    }
}