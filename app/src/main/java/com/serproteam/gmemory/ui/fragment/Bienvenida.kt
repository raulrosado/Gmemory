package com.serproteam.gmemory.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serproteam.gmemory.R
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.databinding.FragmentBienvenidaBinding
import com.serproteam.pideloapp.core.TinyDB
import dagger.hilt.android.AndroidEntryPoint
import org.intellij.lang.annotations.JdkConstants
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Bienvenida.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Bienvenida : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentBienvenidaBinding? = null
    private val binding get() = _binding!!

    //    @Inject lateinit var tinyDB: TinyDB
    @Inject
    lateinit var replaceFragment: ReplaceFragment

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
        _binding = FragmentBienvenidaBinding.inflate(inflater, container, false)
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

        binding.btnEmpezar.setOnClickListener {
            replaceFragment(R.id.contenedorFragment, Home(), fragmentTransaction)
        }
//        binding.btnTools.setOnClickListener {
//            replaceFragment(R.id.contenedorFragment, Tools(), fragmentTransaction)
//        }

        binding.btnTelegram.setOnClickListener {
            try{
                var telegramIntent = Intent(Intent.ACTION_VIEW)
                telegramIntent.setData(Uri.parse("https://telegram.me/Raulrosado91"))
                startActivity(telegramIntent)
            }catch (e:Exception){}
        }

        binding.btntWitter.setOnClickListener {
            try{
                var twitterIntent = Intent(Intent.ACTION_VIEW)
                twitterIntent.setData(Uri.parse("https://twitter.com/raulrosado91"))
                startActivity(twitterIntent)
            }catch (e:Exception){}
        }

        binding.btnEmail.setOnClickListener {
            try{
                var twitterIntent = Intent(Intent.ACTION_VIEW)
                twitterIntent.setData(Uri.parse("mailto:name@email.com"))
                startActivity(twitterIntent)
            }catch (e:Exception){}
        }

        binding.btnWhatsapp.setOnClickListener {
            try {
                var trimNumbe: String = "+5353714262"
                var intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse("https://wa.me/" + trimNumbe + "/?text=" + ""))
                startActivity(intent)
            } catch (e: Exception) {
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Bienvenida.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Bienvenida().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}