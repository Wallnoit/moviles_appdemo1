package com.example.app_android_demo1.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tblVehiculo")
data class Vehiculo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int,

    @ColumnInfo(name="marca")
    val marca: String,

    @ColumnInfo(name="modelo")
    val modelo: String,

    @ColumnInfo(name="anio")
    val anio: Int
): Serializable


