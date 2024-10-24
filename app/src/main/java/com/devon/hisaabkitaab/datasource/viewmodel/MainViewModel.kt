package com.devon.hisaabkitaab.datasource.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.datasource.repo.GeneralRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

}