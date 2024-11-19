package com.devon.hisaabkitaab.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.devon.hisaabkitaab.utils.dateformat.DateConverter

@Database(entities =[MeterReadingModel::class], version = 5, exportSchema = false)
@TypeConverters(DateConverter::class)

abstract class AppDB : RoomDatabase()
{
    abstract fun appDao() : AppDao

}