package com.serproteam.gmemory.domain.Repository

import android.app.Application
import com.serproteam.gmemory.data.model.Dao.EstadisticasDao
import com.serproteam.gmemory.data.model.Dao.JugadorDao
import com.serproteam.gmemory.data.model.Entity.Estadistica
import com.serproteam.gmemory.data.model.db.DB
import kotlinx.coroutines.Job
import javax.inject.Inject


class EstadisticasRepository (private var dao:EstadisticasDao) {
    fun insert(estadistica: Estadistica) {
       return dao.insertEstadistica(estadistica)
    }

    fun update(estadistica: Estadistica){
        dao.updateEstadistica(estadistica)
    }

    fun delete(estadistica: Estadistica){
        dao.deleteEstadistica(estadistica)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }

    fun getEstadisticaByLevel(nivel:Int):List<Estadistica> {
        return dao.getEstadisticaByLevel(nivel)
    }

    fun mejorTiempoByLevel(nivel: Int):String{
        return dao.mejorTiempoByLevel(nivel)
    }
}