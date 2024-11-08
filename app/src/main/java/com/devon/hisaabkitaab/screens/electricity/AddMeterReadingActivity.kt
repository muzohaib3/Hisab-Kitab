package com.devon.hisaabkitaab.screens.electricity

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.devon.hisaabkitaab.R
import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.databinding.ActivityAddMeterReadingBinding
import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import com.devon.hisaabkitaab.extensions.click
import com.devon.hisaabkitaab.extensions.progressDialog
import com.devon.hisaabkitaab.extensions.setToolbar
import com.devon.hisaabkitaab.extensions.show
import com.devon.hisaabkitaab.extensions.stringToDate
import com.devon.hisaabkitaab.extensions.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar


class AddMeterReadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMeterReadingBinding
    private val viewModel: MainViewModel by viewModel()
    private var exception = Exception()
    private lateinit var selectedDate:String
    private val scope = CoroutineScope(Dispatchers.IO)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMeterReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        setToolbar(this, getString(R.string.add_reading),show = true)

        supportActionBar?.hide()
        onClickItems()
    }

    private fun onClickItems(){

        binding.tvDate.click {
            showDatePickerDialog()
        }

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
//                var date = binding.etDate.text.toString()
                var totalCount = binding.etUnitCount.text.toString()

                when
                {
                    readingData.isNullOrEmpty()->{
                        toast("readingData missing")
                    }
//                    date.isNullOrEmpty()->{
//                        toast("date missing")
//                    }

                    totalCount.isNullOrEmpty()->{
                        toast("totalCount missing")
                    }
                    else->{
                        println("converted date is ${stringToDate(selectedDate)}")
//                        var convertedDate = stringToDate(selectedDate)
                        val meterReading = MeterReadingModel(selectedDate,readingData,totalCount)
                        viewModel.insertReading(meterReading)
                        progressDialog(this).show(true)
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


        scope.launch {
            val data = viewModel.selectById(id)
            var mReading = data.meter_reading
            var mDate = data.date
            var mCount = data.total_no_count

            println("The values are addMeterReading >> $mReading $mDate $mCount")
        }

        var meterReading = binding.etMeterReading.text.toString()
//        var date = binding.etDate.text.toString()
        var totalUnit = binding.etUnitCount.text.toString()

        when
        {
            meterReading.isNullOrEmpty()->{
                toast("meterReading missing")
            }
//            date.isNullOrEmpty()->{
//                toast("date missing")
//            }
            totalUnit.isNullOrEmpty()->{
                toast("totalUnit missing")
            }
            else->{
                println("converted date is ${stringToDate(selectedDate)}")
                editMeterReading(id,meterReading,selectedDate,totalUnit)
                progressDialog(this).show(true)
                toast("updated")
                finish()
            }
        }
    }

    private fun editMeterReading(id:Int,date:String,noCount:String, meterData:String){
        viewModel.updateMeterReading(date,meterData,noCount,id)
    }

    private fun showDatePickerDialog() {

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.tvDate.text = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            }, year, month, day)
        datePickerDialog.show()
    }


}