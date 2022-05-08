package com.serproteam.gmemory.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.serproteam.gmemory.Adapter.SlidePagerAdapterSlider
import com.serproteam.gmemory.R
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.databinding.FragmentHomeBinding
import com.serproteam.gmemory.databinding.FragmentOpcionesJuegoBinding
import com.serproteam.gmemory.ui.viewmodel.SliderViewModel
import com.serproteam.pideloapp.core.TinyDB
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OpcionesJuego.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class OpcionesJuego : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentOpcionesJuegoBinding? = null
    private val binding get() = _binding!!
    @Inject lateinit var replaceFragment : ReplaceFragment

    var sliderPager: ViewPager? = null
    var pagerAdapter: PagerAdapter? = null

    var current_position = 0
    var fragmentList: ArrayList<Fragment>? = null
    private val slideViewModel: SliderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOpcionesJuegoBinding.inflate(inflater, container, false)
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        fragmentList = ArrayList<Fragment>()
        fragmentList = slideViewModel.getSliderData()
        Log.v("GmemoryLog", "tamaño lista: " + fragmentList?.size)

        pagerAdapter =
            SlidePagerAdapterSlider(requireActivity().getSupportFragmentManager(), fragmentList)
        binding.sliderPager.adapter = pagerAdapter

        preparetDots(current_position++)
        binding.sliderPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
                if (current_position > 4) {
                    current_position = 0
                }
                preparetDots(i)
            }

            override fun onPageScrollStateChanged(i: Int) {}
        })


        return binding.root
    }

    fun preparetDots(currentSlidePosition: Int) {
        if (binding.dostContainer.getChildCount() > 0) {
            binding.dostContainer.removeAllViews()
        }
        val dots: Array<ImageView?> = arrayOfNulls<ImageView>(fragmentList!!.size)
        for (i in fragmentList!!.indices) {
            dots[i] = ImageView(requireContext())
            if (i == currentSlidePosition) dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.dost
                )
            ) else dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.inactive_dost
                )
            )
            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(4, 0, 4, 0)
            binding.dostContainer.addView(dots[i], layoutParams)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OpcionesJuego.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OpcionesJuego().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

