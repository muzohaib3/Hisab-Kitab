package com.devon.hisaabkitaab.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "electricity_readings")
data class MeterReadingModel(

    @ColumnInfo(name = "date") var date: String = "",
    @ColumnInfo(name = "meter_reading") var meter_reading: String = "",
    @ColumnInfo(name = "total_no_count") var total_no_count: String = ""
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}