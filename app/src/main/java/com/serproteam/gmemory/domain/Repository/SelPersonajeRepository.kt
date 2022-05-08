package com.serproteam.gmemory.domain.Repository

import android.content.Context
import androidx.fragment.app.Fragment
import com.serproteam.gmemory.data.model.services.GetSliderList
import com.serproteam.pideloapp.core.TinyDB
import javax.inject.Inject

class SelPersonajeRepository @Inject constructor(){
    fun saveLevel(context: Context,personaje:String){
        var tinyDB = TinyDB(context)
        tinyDB.putString("personaje", personaje)
    }
}