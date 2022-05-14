package com.serproteam.gmemory.ui.fragment

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.icu.number.IntegerWidth
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.FrameLayout
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.serproteam.gmemory.Adapter.CartasAdaptadores
import com.serproteam.gmemory.R
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.data.model.Dao.EstadisticasDao
import com.serproteam.gmemory.data.model.Entity.Estadistica
import com.serproteam.gmemory.data.model.db.DB
import com.serproteam.gmemory.databinding.DialogTimeBinding
import com.serproteam.gmemory.databinding.FragmentJuegoBinding
import com.serproteam.gmemory.domain.Entitys.Cartas
import com.serproteam.gmemory.domain.Repository.EstadisticasRepository
import com.serproteam.gmemory.ui.viewmodel.EstadisticasViewModel
import com.serproteam.gmemory.ui.viewmodel.EstadisticasViewModelFactory
import com.serproteam.gmemory.ui.viewmodel.PersonajesViewModel
import com.serproteam.gmemory.ui.viewmodel.SliderViewModel
import com.serproteam.pideloapp.core.TinyDB
import com.tapadoo.alerter.Alerter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JuegoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class JuegoFragment : Fragment(), CartasAdaptadores.OnCartaClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentJuegoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var replaceFragment: ReplaceFragment
    lateinit var tinyDB: TinyDB

    private val personajesViewModel: PersonajesViewModel by viewModels()
    private val slideViewModel: SliderViewModel by viewModels()
    lateinit var estadisticasViewModel: EstadisticasViewModel

    private var isCardFront = true
    var arrayCartas = ArrayList<Cartas>()
    var level = 0;
    var isWorking = false
    var primerSel = false
    var primerNum = -1;
    var primerPos = -1;
    val cantParejas = MutableLiveData<Int>()
    val cambioCarta = MutableLiveData<Boolean>()
    var cartasAdaptadores: CartasAdaptadores? = null
    var runnable: Runnable? = null
    var handler = Handler()
    val TIEMPO = 500
    var cantCartasLevel = 0
    var cantClick = 0
    var builder: Dialog? = null
    var tiempo = 60;

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
        _binding = FragmentJuegoBinding.inflate(inflater, container, false)
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        tinyDB = TinyDB(requireContext())
        cantParejas.postValue(0)
        binding.cMeter.setBase(SystemClock.elapsedRealtime());

        val dao: EstadisticasDao = DB.createDB(requireActivity().application).estadisticaDao
        val amigosRepository = EstadisticasRepository(dao)
        val factory = EstadisticasViewModelFactory(amigosRepository)

        builder = Dialog(requireActivity());
        builder!!.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        builder!!.setContentView(R.layout.dialog_time);
        builder!!.setCancelable(true);
        builder!!.setCanceledOnTouchOutside(true);
        builder!!.findViewById<Button>(R.id.btnSelectTime).setOnClickListener {
            tiempo = builder!!.findViewById<SeekBar>(R.id.seekBar2).progress * 60
            builder!!.hide()
            binding.progressBar2.max = tiempo;
            binding.progressBar2.visibility = View.VISIBLE;
        }

        estadisticasViewModel = ViewModelProvider(
            this,
            factory
        ).get(EstadisticasViewModel::class.java)

        binding.btnBack.setOnClickListener {
            replaceFragment(R.id.contenedorFragment, OpcionesJuego(), fragmentTransaction)
        }

        binding.btnSetTime.setOnClickListener {
            builder!!.show()
        }

        binding.cMeter.setOnChronometerTickListener {
            binding.progressBar2.progress = tiempo--
            Log.v("raul","cambio:"+tiempo)
            if(tiempo == 0){
                Alerter.create(requireActivity())
                    .setIcon(resources.getDrawable(R.drawable.ic_baseline_casino_24))
                    .setBackgroundDrawable(resources.getDrawable(R.color.rojo_h))
                    .setTitle(resources.getString(R.string.tiempoAgotado))
                    .setText(resources.getString(R.string.tiempoAgotadoDesc))
                    .show()
                isWorking = false
                it.stop()
            }
        }

        cantParejas.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.txtCantParejas.text = cantParejas.value.toString()
            var completo = false
            var contador = 0
            for (o in arrayCartas) {
                Log.v("raulDev", "carta:" + o.carta + " estado: " + o.activo);
                if (o.activo == true) {
                    contador++
                }
            }
            if (contador == cantCartasLevel) {
                Log.v("raulDev", "Se completo el juego")
                Alerter.create(requireActivity())
                    .setIcon(resources.getDrawable(R.drawable.ic_baseline_casino_24))
                    .setBackgroundDrawable(resources.getDrawable(R.color.verde_h))
                    .setTitle(resources.getString(R.string.felicidades))
                    .setText(
                        resources.getString(R.string.felicidadesDesc) + " Tiempo de " + binding.cMeter.getText()
                            .toString()
                    )
                    .show()
                binding.cMeter.stop()
                isWorking = false

                estadisticasViewModel.insert(
                    Estadistica(
                        0,
                        level,
                        binding.cMeter.getText().toString(),
                        cantClick
                    )
                )
            }

        })

        level = slideViewModel.getLevel(requireContext())
        var personaje = personajesViewModel.personajeSel(requireContext())

        when (level) {
            1 -> {
                createArray(16, 8)
                setupRecyclerView(4)
            }
            2 -> {
                createArray(24, 12)
                setupRecyclerView(6)
            }
            3 -> {
                createArray(30, 15)
                setupRecyclerView(6)
            }
        }

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("raul", "Fragment back pressed invoked")
                    replaceFragment(R.id.contenedorFragment, OpcionesJuego(), fragmentTransaction)
                }
            }
            )


        cargar()
        return binding.root
    }

    fun cargar() {
        cartasAdaptadores = CartasAdaptadores(
            requireContext(),
            personajesViewModel.personajeSel(requireContext()),
            arrayCartas,
            this
        )
        binding.recyclerTarjetas.adapter = cartasAdaptadores
    }

    fun setupRecyclerView(cantColum: Int) {
        binding.recyclerTarjetas.layoutManager = GridLayoutManager(context, cantColum)
    }

    fun createArray(cant: Int, cantCartas: Int) {
        Log.v("raul", "cant:" + cant)
        binding.txtCantParejasTotal.text = cantCartas.toString() + "-"
        cantCartasLevel = cant
        val random = Random()

        while (arrayCartas.size < cant) {
            val getRandomValue: Int = random.nextInt((cantCartas - 0)) + 1;
            if (arrayCartas.size == 0) {
                arrayCartas.add(Cartas(getRandomValue, false))
            } else {
                var p = 0
                for (o in arrayCartas) {
                    if (o.carta == getRandomValue) {
                        p++
                    }
                }

                if (p <= 1) {
                    arrayCartas.add(Cartas(getRandomValue, false))
                }
            }
        }
        println(arrayCartas.toString())
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JuegoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JuegoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCartaClickListener(
        item: Cartas,
        context: Context,
        cardFaceFront: FrameLayout,
        cardFaceBack: FrameLayout,
        cardFlip: FrameLayout,
        position: Int
    ) {
        Log.v("raul", "carta seleccionada:" + item)
        cantClick++
        binding.cantClick.text = cantClick.toString()
        binding.btnSetTime.visibility = View.GONE
        if (!isWorking) {
            binding.cMeter.start()
            isWorking = true
        }
        if (!item.activo) {
            if (cardFaceBack.visibility == View.VISIBLE) {
                flipCard(requireContext(), cardFaceFront, cardFaceBack, cardFlip)
            } else {
                flipCard(requireContext(), cardFaceBack, cardFaceFront, cardFlip)
            }

            if (primerPos != position) {
                if (primerSel) {
                    Log.v("raul", "se asigno el segundo numero:" + item.carta + "---" + primerNum)
                    Log.v("raul", "posicion del segundo numero:" + position)

                    if (primerNum == item.carta) {
                        var cant = cantParejas.value!! + 1
                        cantParejas.postValue(cant)
                        arrayCartas[position].activo = true
                        arrayCartas[primerPos].activo = true
                    } else {
                        arrayCartas[position].activo = false
                        arrayCartas[primerPos].activo = false
                    }
                    primerNum = -1
                    primerPos = -1
                    primerSel = false

                    val handler = Handler()
                    val runnable: Runnable = object : Runnable {
                        var i = false
                        override fun run() {
                            Log.v("Runnable", "Handler is working: $i")
                            i = true
                            cargar()
                            if (i == false) {
                                handler.postDelayed(
                                    this,
                                    TIEMPO.toLong()
                                )
                            }
                        }
                    }
                    handler.postDelayed(
                        runnable,
                        TIEMPO.toLong()
                    )
                } else {
                    primerNum = item.carta
                    primerPos = position
                    primerSel = true
                    Log.v("raul", "se asigno el primer numero:" + item)
                    Log.v("raul", "posicion del primer numero:" + position)
                }
            }
        }
    }

    @SuppressLint("ResourceType")
    private fun flipCard(
        context: Context,
        visibleView: View,
        inVisibleView: View,
        cardFlip: FrameLayout
    ) {
        try {
            visibleView.visibility = View.VISIBLE
            val scale = context.resources.displayMetrics.density
            val cameraDist = 8000F * scale
            visibleView.cameraDistance = cameraDist
            inVisibleView.cameraDistance = cameraDist
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(context, R.anim.flip_out) as AnimatorSet
            flipOutAnimatorSet.setTarget(inVisibleView)
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(context, R.anim.flip_in) as AnimatorSet
            flipInAnimatorSet.setTarget(visibleView)

            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()
            flipInAnimatorSet.doOnEnd {
                inVisibleView.visibility = View.GONE
                cardFlip.isEnabled = true
            }
        } catch (e: Exception) {
            Log.v("raul", "flipCard: $e")
        }
    }

}
