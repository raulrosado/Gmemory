package com.serproteam.gmemory.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.serproteam.gmemory.R
import com.serproteam.gmemory.core.ReplaceFragment
import com.serproteam.gmemory.databinding.ActivityMainBinding
import com.serproteam.gmemory.ui.fragment.Bienvenida
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var replaceFragment : ReplaceFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(binding.root)
        configInicio()
    }

    private fun configInicio() {
        val bienvenida = Bienvenida()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        replaceFragment(R.id.contenedorFragment, bienvenida, fragmentTransaction)
    }
}