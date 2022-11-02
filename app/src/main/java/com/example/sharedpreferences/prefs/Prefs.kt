package com.example.sharedpreferences.prefs

import android.content.Context


class Prefs(c: Context) {
    val storage = c.getSharedPreferences("ALMACEN", 0)

    fun guardarUltimaVisita(fecha : String) {
        storage.edit().putString("FECHA", fecha).apply()
    }

    fun guardarNumeroVisitas(visitas : Int) {
        storage.edit().putInt("VISITAS", visitas).apply()
    }

    fun leerUltimaVisita() : String {
        return storage.getString("FECHA", "").toString()
    }

    fun leerNumeroVisitas() : Int {
        return storage.getInt("VISITAS", 0)
    }

    //Funcion para borrar todos los datos de preferencias
    fun delete() {
        storage.edit().clear().apply()
    }
}