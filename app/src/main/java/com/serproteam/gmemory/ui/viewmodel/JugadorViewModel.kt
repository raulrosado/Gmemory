package com.serproteam.gmemory.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serproteam.gmemory.data.model.Entity.Jugador
import com.serproteam.gmemory.domain.Repository.JugadorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class JugadorViewModel(private val jugadorRepository: JugadorRepository):ViewModel() {
    fun getInfoJugador(): LiveData<Jugador> {
        return jugadorRepository.getInfoJugador()
    }

    fun insert(jugador: Jugador): Job = viewModelScope.launch { jugadorRepository.insert(jugador) }
    fun update(jugador: Jugador): Job = viewModelScope.launch { jugadorRepository.update(jugador) }
    fun delete(jugador: Jugador): Job = viewModelScope.launch { jugadorRepository.delete(jugador) }
}