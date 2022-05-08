package com.serproteam.gmemory.domain.usecase

import android.content.Context
import androidx.fragment.app.Fragment
import com.serproteam.gmemory.domain.Repository.GetPersonajeRepository
import javax.inject.Inject

class GetPersonajeUseCase @Inject constructor(private var getPersonajeRepository: GetPersonajeRepository){
    operator fun invoke(context:Context):String{
        return getPersonajeRepository.getPersonaje(context)
    }
}