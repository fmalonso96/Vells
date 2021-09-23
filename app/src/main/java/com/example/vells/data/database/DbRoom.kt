package com.example.vells.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TableEntity::class,ProductEntity::class], version = 1)
abstract class DbRoom: RoomDatabase() {

    abstract val productDao: ProductDao
    abstract val tableDao: TableDao

    companion object {

        @Volatile
        private var INSTANCE: DbRoom? = null

        fun getDatabase(context: Context) : DbRoom {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DbRoom::class.java,
                        "Vells Database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}