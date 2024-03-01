package com.example.socialpics.modelo

data class User (
    var idUser: Int=0,
    var nombreUser:String="",
    var email:String="",
    var pass:String="",
    var seguidos:List<Follow> = emptyList(),
    var seguidores:List<Follow> =emptyList()
)