package com.serproteam.gmemory.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.serproteam.gmemory.R
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.data.model.Dao.EstadisticasDao
import com.serproteam.gmemory.data.model.db.DB
import com.serproteam.gmemory.databinding.FragmentBienvenidaBinding
import com.serproteam.gmemory.databinding.FragmentHomeBinding
import com.serproteam.gmemory.domain.Repository.EstadisticasRepository
import com.serproteam.gmemory.ui.viewmodel.EstadisticasViewModel
import com.serproteam.gmemory.ui.viewmodel.EstadisticasViewModelFactory
import com.serproteam.pideloapp.core.TinyDB
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
//    @Inject lateinit var tinyDB: TinyDB
    @Inject lateinit var replaceFragment : ReplaceFragment
    lateinit var estadisticasViewModel:EstadisticasViewModel

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        inicio()
        return binding.root
    }

    private fun inicio() {
        val dao: EstadisticasDao = DB.createDB(requireActivity().application).estadisticaDao
        val amigosRepository = EstadisticasRepository(dao)
        val factory = EstadisticasViewModelFactory(amigosRepository)

        estadisticasViewModel = ViewModelProvider(
            this,
            factory
        ).get(EstadisticasViewModel::class.java)

        var fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        binding.btnNewGame.setOnClickListener { replaceFragment(R.id.contenedorFragment, OpcionesJuego(), fragmentTransaction) }

        binding.txtTimeFacil.text = estadisticasViewModel.mejorTiempoByLevel(1)
        binding.txtTimeMedio.text = estadisticasViewModel.mejorTiempoByLevel(2)
        binding.txtTimeAlto.text = estadisticasViewModel.mejorTiempoByLevel(3)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}