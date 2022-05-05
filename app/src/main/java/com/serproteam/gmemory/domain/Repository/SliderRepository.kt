package com.serproteam.gmemory.domain.Repository

import androidx.fragment.app.Fragment
import com.serproteam.gmemory.data.model.services.GetSliderList
import javax.inject.Inject

class SliderRepository @Inject constructor(private var getSliderList : GetSliderList){
    fun getSliders():ArrayList<Fragment>{
        return getSliderList.getSliderList()
    }
}