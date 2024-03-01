package com.example.socialpics.modelo

data class Nota(
    var idnota: Int = 0,
    var idUser: Int = 0,
    var likes: List<Likes> = emptyList(),
    var mensaje: String = ""
)