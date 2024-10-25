package com.devon.hisaabkitaab.datasource.repo

import com.devon.hisaabkitaab.database.MeterReadingModel
import com.devon.hisaabkitaab.database.AppDao

class GeneralRepository(private val appDao: AppDao) {

    fun insertReading(reading: MeterReadingModel) {
        appDao.insertReading(reading)
    }

    fun getAllReadings(): List<MeterReadingModel> {
        return appDao.getAllReadings()
    }

    fun updateMeterReadings(date:String,meter_reading:String,total_no_count:String,id:Int ){
        println("The GeneralRepository function is called")
        appDao.updateMeterReadings(date, meter_reading, total_no_count, id)
    }
}