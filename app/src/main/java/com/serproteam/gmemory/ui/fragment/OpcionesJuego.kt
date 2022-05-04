package com.serproteam.gmemory.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.serproteam.gmemory.R
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.databinding.FragmentHomeBinding
import com.serproteam.gmemory.databinding.FragmentOpcionesJuegoBinding
import com.serproteam.gmemory.ui.viewmodel.SliderViewModel
import com.serproteam.pideloapp.core.TinyDB

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OpcionesJuego.newInstance] factory method to
 * create an instance of this fragment.
 */
class OpcionesJuego : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentOpcionesJuegoBinding? = null
    private val binding get() = _binding!!
    lateinit var tinyDB: TinyDB
    val replaceFragment = ReplaceFragment()

    var current_position = 0
    var fragmentList: ArrayList<Fragment>? = null


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
        tinyDB = TinyDB(requireContext())
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()



        return binding.root
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

