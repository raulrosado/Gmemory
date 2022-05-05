package com.serproteam.gmemory.domain.usecase

import androidx.fragment.app.Fragment
import com.serproteam.gmemory.domain.Repository.SliderRepository
import javax.inject.Inject

class GetSliderUserCase @Inject constructor(private var sliderRepository : SliderRepository) {
    operator fun invoke():ArrayList<Fragment>{
        return sliderRepository.getSliders()
    }
}