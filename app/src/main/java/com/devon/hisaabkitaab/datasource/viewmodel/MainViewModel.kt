package com.devon.hisaabkitaab.datasource.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.datasource.repo.GeneralRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: GeneralRepository):ViewModel()
{
    fun insertReading(reading: MeterReadingModel) {
        viewModelScope.launch (Dispatchers.IO){
            repository.insertReading(reading)
        }
    }

    fun fetchAllReadings(onResult: (List<MeterReadingModel>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val readings = repository.getAllReadings()
            onResult(readings)
        }
    }

    fun updateMeterReading(date:String,meter_reading:String,total_no_count:String,id:Int ){
        viewModelScope.launch(Dispatchers.IO) {
            println("The viewModelScope function is called")
            repository.updateMeterReadings(date, meter_reading, total_no_count, id)
        }
    }

    fun deleteMReading(index:Int){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteMReadingById(index)
        }
    }

    suspend fun selectById(index: Int):MeterReadingModel{
        return withContext(Dispatchers.IO){
            repository.selectById(index)
        }
    }

    suspend fun getMeterReadingSelectedByMonth(month:String):List<MeterReadingModel>{
        return withContext(Dispatchers.IO){
            repository.getMeterReadingSelectedByMonth(month)
        }
    }

}