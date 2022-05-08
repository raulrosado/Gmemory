package com.serproteam.gmemory.domain.usecase

import android.content.Context
import androidx.fragment.app.Fragment
import com.serproteam.gmemory.domain.Repository.SelLevelRepository
import com.serproteam.gmemory.domain.Repository.SelPersonajeRepository
import javax.inject.Inject

class SelPersonajelUseCase @Inject constructor(private var selPersonajeRepository: SelPersonajeRepository){
    operator fun invoke(context: Context,personaje:String){
        return selPersonajeRepository.saveLevel(context,personaje)
    }
}