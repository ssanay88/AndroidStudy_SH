package com.example.androidstudy_sh.DB

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostData::class], version = 1)
abstract class PostDB : RoomDatabase() {
    abstract fun getDao(): DAO
}