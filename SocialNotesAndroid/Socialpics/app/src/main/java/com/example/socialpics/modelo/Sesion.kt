package com.example.socialpics.modelo

object Sesion {
    private var idSesion: Int = 100

    fun getSesionUsuario(): Int {
        return idSesion
    }

    fun setSesionUsuario(id: Int) {
        idSesion = id
    }
}