package com.serproteam.gmemory.domain.Repository

import androidx.fragment.app.Fragment
import com.serproteam.gmemory.data.model.services.GetSliderList

class SliderRepository {
    var getSliderList = GetSliderList()

    fun getSliders():ArrayList<Fragment>{
        return getSliderList.getSliderList()
    }
}