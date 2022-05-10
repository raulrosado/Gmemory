package com.serproteam.gmemory.data.model.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.serproteam.gmemory.data.model.Entity.Estadistica
import com.serproteam.gmemory.data.model.Entity.Jugador

@Dao
interface JugadorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJugador(jugador: Jugador): Long

    @Update
    suspend fun updateJugador(jugador: Jugador)

    @Delete
    suspend fun deleteJugador(jugador: Jugador)

    @Query("DELETE FROM jugador")
    suspend fun deleteAll()

    @Query("SELECT * FROM jugador")
    fun getInfoJugador() :LiveData<Jugador>
}