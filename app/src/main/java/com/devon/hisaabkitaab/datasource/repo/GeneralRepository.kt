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
}