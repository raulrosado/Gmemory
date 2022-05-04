package com.serproteam.gmemory.ui.viewmodel

import androidx.fragment.app.Fragment
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.domain.usecase.GetSliderUserCase

class SliderViewModel {
    val replaceFragment = ReplaceFragment()

    fun getSliderData(): ArrayList<Fragment> {
        return GetSliderUserCase().invoke()
    }

}