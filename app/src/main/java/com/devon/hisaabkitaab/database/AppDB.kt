package com.devon.hisaabkitaab.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities =[MeterReadingModel::class], version = 4, exportSchema = false)

abstract class AppDB : RoomDatabase()
{
    abstract fun appDao() : AppDao

}