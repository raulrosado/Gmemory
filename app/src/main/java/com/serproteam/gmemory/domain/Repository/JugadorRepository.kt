package com.serproteam.gmemory.domain.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.serproteam.gmemory.data.model.Dao.EstadisticasDao
import com.serproteam.gmemory.data.model.Dao.JugadorDao
import com.serproteam.gmemory.data.model.Entity.Estadistica
import com.serproteam.gmemory.data.model.Entity.Jugador
import com.serproteam.gmemory.data.model.db.DB


class JugadorRepository(private var dao:JugadorDao) {
    suspend fun insert(jugador: Jugador){
        dao.insertJugador(jugador)
    }

    suspend fun update(jugador: Jugador){
        dao.updateJugador(jugador)
    }

    suspend fun delete(jugador: Jugador){
        dao.deleteJugador(jugador)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }

    fun getInfoJugador():LiveData<Jugador>{
        return dao.getInfoJugador()
    }


}