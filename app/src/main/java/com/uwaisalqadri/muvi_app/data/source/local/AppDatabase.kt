package com.uwaisalqadri.muvi_app.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.uwaisalqadri.muvi_app.data.source.local.entity.MovieEntity

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}