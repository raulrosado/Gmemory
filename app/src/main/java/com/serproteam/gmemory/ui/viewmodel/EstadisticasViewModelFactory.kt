package com.serproteam.gmemory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.serproteam.gmemory.data.model.Entity.Estadistica
import com.serproteam.gmemory.data.model.Entity.Jugador
import com.serproteam.gmemory.domain.Repository.EstadisticasRepository
import com.serproteam.gmemory.domain.Repository.JugadorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class EstadisticasViewModelFactory(var estadisticasRepository: EstadisticasRepository):
    ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EstadisticasViewModel::class.java)){
            return EstadisticasViewModel(estadisticasRepository) as T
        }
        throw IllegalAccessException("Unknown View Model class")
    }

}