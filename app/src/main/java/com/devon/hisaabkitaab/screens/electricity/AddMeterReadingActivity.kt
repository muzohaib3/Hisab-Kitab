package com.devon.hisaabkitaab.screens.electricity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devon.hisaabkitaab.R
import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.databinding.ActivityAddMeterReadingBinding
import com.devon.hisaabkitaab.datasource.viewmodel.MainViewModel
import com.devon.hisaabkitaab.utils.click
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

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


        binding.btAddMeterUnits.click {
            insertMeterReading()
        }

    }

    private fun insertMeterReading()
    {
        kotlin.runCatching {

            var readingData = binding.etMeterReading.text.toString()
            var date = binding.etDate.text.toString()
            var totalCount = binding.etUnitCount.text.toString()

            val meterReading = MeterReadingModel(date,readingData,totalCount)
            viewModel.insertReading(meterReading)

            finish()

        }.onFailure {

            println("The exception => ${exception.message}")
        }

    }
}