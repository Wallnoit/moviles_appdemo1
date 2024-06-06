package com.example.app_android_demo1.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_android_demo1.models.Vehiculo

@Dao
interface VehiculoDao {

    //este dao sirve para definir el CRUD

    @Insert
    fun insert(vehiculo: Vehiculo):Long

    @Update
    fun update(vehiculo: Vehiculo)

    @Delete
    fun delete(vehiculo: Vehiculo)
    @Query("select * from tblVehiculo where id=:idInput")
    fun getVehiculo(idInput:Int):Vehiculo

    @Query("select * from tblVehiculo order by id")
    fun getVehiculos():LiveData<List<Vehiculo>>


}