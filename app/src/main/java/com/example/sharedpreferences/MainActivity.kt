package com.example.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sharedpreferences.databinding.ActivityMainBinding
import com.example.sharedpreferences.prefs.Prefs
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var fecha = ""
    var visitas = 0
    lateinit var prefs: Prefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Añadimos preferencias
        prefs = Prefs(this)
        guardarDatos()
        mostrarDatos()
        setListeners()
    }

    private fun setListeners() {
        binding.btnReset.setOnClickListener {
            reset()
        }
        binding.btnSalir.setOnClickListener {
            finish()
        }
    }

    private fun guardarDatos() {
        visitas = prefs.leerNumeroVisitas()
        visitas++
        prefs.guardarNumeroVisitas(visitas)
        var fechaHoyNoFormat = LocalDateTime.now()
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        var fechaFormateada = fechaHoyNoFormat.format(formato)
        //println("=========================================>${prefs.leerNumeroVisitas()}, ${prefs.leerUltimaVisita()}")
        prefs.guardarUltimaVisita(fechaFormateada)
    }

    private fun reset() {
        visitas = 0
        fecha = ""
        prefs.delete()
        mostrarDatos()
    }

    private fun mostrarDatos() {
        fecha = prefs.leerUltimaVisita()
        if (fecha.isEmpty()) {
            binding.tvFecha.text = "Es la primera visita"
        } else {
            binding.tvFecha.text = String.format(getString(R.string.fecha_visita), fecha)
        }
        visitas = prefs.leerNumeroVisitas()
        binding.tvVisitas.text = String.format(getString(R.string.num_visitas), visitas)
    }
}