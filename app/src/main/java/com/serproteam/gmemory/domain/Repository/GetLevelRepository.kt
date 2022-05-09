package com.serproteam.gmemory.domain.Repository

import android.content.Context
import androidx.fragment.app.Fragment
import com.serproteam.gmemory.data.model.services.GetSliderList
import com.serproteam.pideloapp.core.TinyDB
import javax.inject.Inject

class GetLevelRepository @Inject constructor(){
    fun getLevel(context: Context):Int{
        var tinyDB = TinyDB(context)
        return tinyDB.getInt("level",0)!!
    }
}