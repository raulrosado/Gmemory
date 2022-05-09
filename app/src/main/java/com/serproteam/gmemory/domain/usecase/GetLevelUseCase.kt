package com.serproteam.gmemory.domain.usecase

import android.content.Context
import androidx.fragment.app.Fragment
import com.serproteam.gmemory.domain.Repository.GetLevelRepository
import com.serproteam.gmemory.domain.Repository.GetPersonajeRepository
import javax.inject.Inject

class GetLevelUseCase @Inject constructor(private var getLevelRepository: GetLevelRepository){
    operator fun invoke(context:Context):Int{
        return getLevelRepository.getLevel(context)
    }
}