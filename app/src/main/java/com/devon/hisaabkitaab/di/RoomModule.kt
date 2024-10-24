package com.devon.hisaabkitaab.di

import android.content.Context
import androidx.room.Room
import com.devon.hisaabkitaab.database.AppDB
import org.koin.dsl.module

val RoomModule = module {

    single { provideDatabase(get()) }

    // Provide the DAO instance
    single { get<AppDB>().appDao() }
}

// Function to provide the Room database
fun provideDatabase(context: Context): AppDB {
    return Room.databaseBuilder(context, AppDB::class.java, "details_db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}