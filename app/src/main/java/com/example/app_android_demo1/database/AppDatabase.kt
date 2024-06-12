package com.example.app_android_demo1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app_android_demo1.models.Vehiculo

@Database(entities = [Vehiculo::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    //Definir el DAO
    abstract fun vehiculoDao(): VehiculoDao

    //Definir instancia de base de datos
    companion object{
        private var instance: AppDatabase? = null
        fun geInstance(context:Context): AppDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "dbVehiculo"
                ).build()
            }
            return instance as AppDatabase
        }


    }



}