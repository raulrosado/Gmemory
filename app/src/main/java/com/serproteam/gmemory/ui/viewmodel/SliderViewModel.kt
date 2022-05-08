package com.serproteam.gmemory.ui.viewmodel

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.domain.usecase.GetSliderUserCase
import com.serproteam.gmemory.domain.usecase.SelLevelUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(private var replaceFragment : ReplaceFragment, private var getSliderUserCase: GetSliderUserCase, private var selLevelUseCase: SelLevelUseCase) : ViewModel(){
    fun getSliderData(): ArrayList<Fragment> {
        return getSliderUserCase.invoke()
    }

    fun saveLevel(context: Context,level:Int){
        selLevelUseCase.invoke(context,level)
    }

}