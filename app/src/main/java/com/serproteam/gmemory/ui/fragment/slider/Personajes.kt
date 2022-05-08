package com.serproteam.gmemory.ui.fragment.slider

import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.serproteam.gmemory.databinding.FragmentPersonajesBinding
import com.serproteam.pideloapp.core.TinyDB
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Personajes.newInstance] factory method to
 * create an instance of this fragment.
 */
class Personajes : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentPersonajesBinding? = null
    private val binding get() = _binding!!
    lateinit var tinyDB: TinyDB

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
        _binding = FragmentPersonajesBinding.inflate(inflater, container, false)
        tinyDB = TinyDB(requireContext());

        when (tinyDB.getString("personaje","batman")) {
            "batman" -> {
                sel(binding.selecBat, binding.selecSuper)
            }
            "superman" -> {
                sel(binding.selecSuper, binding.selecBat)
            }
        }

        binding.btnBatman.setOnClickListener {
            sel(binding.selecBat, binding.selecSuper)
            selPersonaje("batman")
        }

        binding.btnSuperMan.setOnClickListener {
            sel(binding.selecSuper, binding.selecBat)
            selPersonaje("superman")
        }
        return binding.root
    }

    fun sel(selec: LinearLayout, desSelect: LinearLayout) {
        selec.visibility = View.VISIBLE
        desSelect.visibility = View.GONE
    }

    fun selPersonaje(personaje: String) {
        tinyDB.putString("personaje", personaje)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Personajes.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Personajes().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}