package com.serproteam.gmemory.ui.fragment.slider

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.serproteam.gmemory.R
import com.serproteam.gmemory.databinding.FragmentLevelsBinding
import com.serproteam.pideloapp.core.TinyDB
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Levels.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Levels : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentLevelsBinding? = null
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
        // Inflate the layout for this fragment
        _binding = FragmentLevelsBinding.inflate(inflater, container, false)
        tinyDB = TinyDB(requireContext());

        binding.btnfacil.setOnClickListener {
            activClient(binding.btnfacil,binding.txtFacil,binding.txt4x4)
            saveLevel(0)
        }
        binding.btnmedio.setOnClickListener {
            activClient(binding.btnmedio,binding.txtMedio,binding.txt6x4)
            saveLevel(1)
        }
        binding.btnalto.setOnClickListener {
            activClient(binding.btnalto,binding.txtAlto,binding.txt6x5)
            saveLevel(2)
        }

        return binding.root
    }

    fun saveLevel(level:Int){
        tinyDB.putInt("level",level)
    }

    fun activClient(btn: LinearLayout, txtNivel: TextView, txtCant: TextView) {
        limpiar()
        btn.setBackground(getResources().getDrawable(R.drawable.button_accent));
        txtCant.setTextColor(resources.getColor(R.color.white))
        txtNivel.setTextColor(resources.getColor(R.color.white))
    }

    fun limpiar(){
        binding.btnalto.setBackground(getResources().getDrawable(R.drawable.backgroundblanco));
        binding.btnmedio.setBackground(getResources().getDrawable(R.drawable.backgroundblanco));
        binding.btnfacil.setBackground(getResources().getDrawable(R.drawable.backgroundblanco));
        binding.txtMedio.setTextColor(resources.getColor(R.color.black))
        binding.txtAlto.setTextColor(resources.getColor(R.color.black))
        binding.txtFacil.setTextColor(resources.getColor(R.color.black))
        binding.txt4x4.setTextColor(resources.getColor(R.color.black))
        binding.txt6x4.setTextColor(resources.getColor(R.color.black))
        binding.txt6x5.setTextColor(resources.getColor(R.color.black))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Levels.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Levels().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}