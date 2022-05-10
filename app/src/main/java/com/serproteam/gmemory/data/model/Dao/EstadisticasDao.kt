package com.serproteam.gmemory.data.model.Dao

import androidx.room.*
import com.serproteam.gmemory.data.model.Entity.Estadistica

@Dao
interface EstadisticasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEstadistica(estadistica: Estadistica)

    @Update
    fun updateEstadistica(estadistica: Estadistica)

    @Delete
    fun deleteEstadistica(estadistica: Estadistica)

    @Query("DELETE FROM estadistica")
    suspend fun deleteAll()

    @Query("SELECT * FROM estadistica WHERE nivel <> :nivel")
    fun getEstadisticaByLevel(nivel:Int): List<Estadistica>

    @Query("SELECT tiempo FROM estadistica WHERE nivel == :nivel ORDER BY tiempo ASC LIMIT 1")
    fun mejorTiempoByLevel(nivel: Int):String
}