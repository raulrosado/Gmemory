package com.serproteam.gmemory.ui.viewmodel

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.domain.usecase.GetPersonajeUseCase
import com.serproteam.gmemory.domain.usecase.GetSliderUserCase
import com.serproteam.gmemory.domain.usecase.SelLevelUseCase
import com.serproteam.gmemory.domain.usecase.SelPersonajelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private var replaceFragment : ReplaceFragment,
                                          private var getSliderUserCase: GetSliderUserCase,
                                          private var selLevelUseCase: SelLevelUseCase,
                                          private var selPersonajelUseCase: SelPersonajelUseCase,
                                          private var getPersonajeUseCase: GetPersonajeUseCase) : ViewModel(){
    fun getSliderData(): ArrayList<Fragment> {
        return getSliderUserCase.invoke()
    }

    fun saveLevel(context: Context,level:Int){
        selLevelUseCase.invoke(context,level)
    }

    fun personajeSel(context: Context):String{
        return getPersonajeUseCase(context)
    }

    fun savePersonaje(context: Context,personaje: String){
        selPersonajelUseCase.invoke(context,personaje)
    }
}