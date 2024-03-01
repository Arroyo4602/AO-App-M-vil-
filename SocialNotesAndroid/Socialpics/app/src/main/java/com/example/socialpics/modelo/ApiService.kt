package com.example.socialpics.modelo

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("/main/")
    suspend fun main(): Response<String>

    @POST("/registrar/{nombreUser}/{email}/{pass}/")
    suspend fun registrar(@Path("nombreUser") nombreUser:String,@Path("email") email:String,@Path("pass") pass:String):Response<String>

    @POST("/login/{nombreUser}/{pass}/")
    suspend fun log(@Path("nombreUser") nombreUser:String,@Path("pass") pass:String):Response<String>

    @POST("/postnote/{mensaje}/{idUser}/")
    suspend fun postnote(@Path("mensaje") mensaje:String,@Path("idUser") idUser:Int):Response<String>

    @POST("/getNotas/{idUser}/")
    suspend fun getNotas(@Path("idUser") idUser:Int): List<Nota>

    @POST("/getUser/{idUser}/")
    suspend fun getUser(@Path("idUser") idUser: Int):User

    @POST("/listarUsers/{idUser}/")
    suspend fun listarUsers(@Path("idUser") idUser: Int):List<User>

    @POST("/follow/{idUser}/{idSeguido}/")
    suspend fun follow(@Path("idUser") idUser: Int,@Path("idSeguido") idSeguido:Int)

    @POST("/unfollow/{idUser}/{idSeguido}/")
    suspend fun unfollow(@Path("idUser") idUser: Int,@Path("idSeguido") idSeguido:Int)

    @POST("/getHomeNotes/{idUser}/")
    suspend fun listar(@Path("idUser") idUser: Int): List<Nota>

    @POST("/likeNota/{idNota}/{idUser}/")
    suspend fun like(@Path("idNota") idNota: Int,@Path("idUser") idUser: Int)

    @POST("/dislikeNota/{idNota}/{idUser}/")
    suspend fun dislike(@Path("idNota") idNota: Int,@Path("idUser") idUser: Int)

    @POST("/borrarNota/{idNota}/")
    suspend fun borrarNota(@Path("idNota") idNota: Int)
}