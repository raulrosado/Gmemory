package com.serproteam.gmemory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serproteam.gmemory.data.model.Entity.Estadistica
import com.serproteam.gmemory.data.model.Entity.Jugador
import com.serproteam.gmemory.domain.Repository.EstadisticasRepository
import com.serproteam.gmemory.domain.Repository.JugadorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class EstadisticasViewModel(var estadisticasRepository: EstadisticasRepository):ViewModel() {
    fun getEstadisticaByLevel(level:Int): List<Estadistica> {
        return estadisticasRepository!!.getEstadisticaByLevel(level)
    }
    fun mejorTiempoByLevel(nivel: Int):String {
        return estadisticasRepository!!.mejorTiempoByLevel(nivel)
    }

    fun insert(estadistica: Estadistica) = estadisticasRepository!!.insert(estadistica)
    fun update(estadistica: Estadistica) = estadisticasRepository!!.update(estadistica)
    fun delete(estadistica: Estadistica) = estadisticasRepository!!.delete(estadistica)
}