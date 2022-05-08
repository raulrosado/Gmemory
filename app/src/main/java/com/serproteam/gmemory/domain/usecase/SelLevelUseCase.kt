package com.serproteam.gmemory.domain.usecase

import android.content.Context
import androidx.fragment.app.Fragment
import com.serproteam.gmemory.domain.Repository.SelLevelRepository
import javax.inject.Inject

class SelLevelUseCase @Inject constructor(private var selLevelRepository: SelLevelRepository){
    operator fun invoke(context: Context,level:Int){
        return selLevelRepository.saveLevel(context,level)
    }
}