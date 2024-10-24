package com.devon.hisaabkitaab.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devon.hisaabkitaab.database.MeterReadingModel

@Dao
interface AppDao {

    @Insert
    fun insertReading(reading: MeterReadingModel)

    @Query("SELECT * FROM electricity_readings")
    fun getAllReadings(): List<MeterReadingModel>

}