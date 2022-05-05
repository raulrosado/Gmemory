package com.serproteam.gmemory.Adapter

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import javax.inject.Inject

class SlidePagerAdapterSlider(
    fm: FragmentManager,
    private val fragmentList: ArrayList<Fragment>?
) :
    FragmentStatePagerAdapter(fm) {


    private val titles = arrayOf(
        "Levels",
        "Personajes"
    )

    override fun getCount(): Int {
        return fragmentList!!.size!!;
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList!!.get(position);
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position].toString();
    }

    override fun restoreState(state: Parcelable?, loader: ClassLoader?) {
        try {
            super.restoreState(state, loader)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}