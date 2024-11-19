package com.devon.hisaabkitaab.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.devon.hisaabkitaab.database.MeterReadingModel

@Dao
interface AppDao {

    @Insert
    fun insertReading(reading: MeterReadingModel)

    @Query("SELECT * FROM electricity_readings")
    fun getAllReadings(): List<MeterReadingModel>

    @Query("update electricity_readings set date=:date, meter_reading=:meter_reading, total_no_count=:total_no_count where id=:id")
    fun updateMeterReadings(date:String,meter_reading:String,total_no_count:String,id:Int )

    @Query("delete from electricity_readings where id=:id")
    fun deleteMReadingById(id: Int)

    @Query("select * from electricity_readings where id=:index")
    fun selectById(index:Int):MeterReadingModel

    @Query("select * from electricity_readings where strftime('%Y-%m', date) =:date;")
    fun getFilteredMeterReadingsByMonth(date: String): List<MeterReadingModel>



}