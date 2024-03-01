package com.example.socialpics.modelo

object ProfileAjeno {
    private var idProfileAjeno: Int = 100

    fun getSesionUsuario(): Int {
        return idProfileAjeno
    }

    fun setSesionUsuario(id: Int) {
        idProfileAjeno = id
    }

}