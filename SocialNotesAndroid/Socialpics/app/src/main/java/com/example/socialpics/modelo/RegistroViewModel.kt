package com.example.socialpics.modelo


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Response
import java.io.File


class RegistroViewModel: ViewModel() {


    suspend fun home(){
        try {
            val respuesta=RetrofitService.instance.main()
            if (respuesta.isSuccessful){
                Log.d("RegistroViewModel", respuesta.body().toString())
            } else {
                Log.e("RegistroViewModel", "Error en la respuesta: ${respuesta.code()}")
            }
        }catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
    }

    suspend fun registrar(nombreUser:String,email:String,pass:String): String {
        var response=""
        try {
            val respuesta=RetrofitService.instance.registrar(nombreUser,email,pass)
            response=respuesta.body().toString()
            if (respuesta.isSuccessful){
                Log.d("RegistroViewModel", respuesta.body().toString())
            } else {
                Log.e("RegistroViewModel", "Error en la respuesta: ${respuesta.code()}")
            }
        }catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
        return response
    }

    suspend fun log(nombreUser: String, password: String) :String{
        var response=""
        try {
            val respuesta = RetrofitService.instance.log(nombreUser, password)
            response=respuesta.body().toString()
            if (respuesta.isSuccessful) {
                val responseBody = respuesta.body()
                if (responseBody != null) {
                    Log.d("RegistroViewModel", responseBody)
                } else {
                    Log.d("RegistroViewModel", "La respuesta no contiene un cuerpo válido")
                }
            } else {
                Log.d("RegistroViewModel", "La llamada a la API no fue exitosa: ${respuesta.code()}")
            }
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
        return response
    }

    suspend fun postnote(mensaje:String,idUser:Int){
        try {
            val respuesta = RetrofitService.instance.postnote(mensaje,idUser)
            if (respuesta.isSuccessful) {
                val responseBody = respuesta.body()
                if (responseBody != null) {
                    Log.d("RegistroViewModel", responseBody)
                } else {
                    Log.d("RegistroViewModel", "La respuesta no contiene un cuerpo válido")
                }
            } else {
                Log.d("RegistroViewModel", "La llamada a la API no fue exitosa: ${respuesta.code()}")
            }
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
    }

    suspend fun getNotas(idUser: Int): List<Nota> {
        var notasaux: List<Nota> = emptyList()
        try {
            val notas = RetrofitService.instance.getNotas(idUser)
            Log.d("RegistroViewModel", notas.toString())
            notasaux=notas
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
        return notasaux
    }

    suspend fun getUser(idUser:Int):User{
        var user:User=User()
        try {
            val useraux=RetrofitService.instance.getUser(idUser)
            Log.d("RegistroViewModel", useraux.toString())
            user=useraux
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
        return user
    }

    suspend fun listarUsers(idUser: Int):List<User>{
        var listaAux: List<User> = emptyList()
        try {
            val list=RetrofitService.instance.listarUsers(idUser)
            Log.d("RegistroViewModel", list.toString())
            listaAux=list
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
        return listaAux
    }

    suspend fun follow(idUser: Int,idSeguido:Int){
        try {
            RetrofitService.instance.follow(idUser,idSeguido)
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
    }

    suspend fun unfollow(idUser: Int,idSeguido:Int){
        try {
            RetrofitService.instance.unfollow(idUser,idSeguido)
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
    }
   suspend fun listar(idUser: Int):List<Nota>{
       var notasaux: List<Nota> = emptyList()
       try {
           val notas = RetrofitService.instance.listar(idUser)
           Log.d("RegistroViewModel", notas.toString())
           notasaux=notas
       } catch (e: Exception) {
           Log.e("RegistroViewModel", "Error general: ${e.message}")
           e.printStackTrace()
       }
       return notasaux
   }

    suspend fun like(idNota: Int,idUser: Int){
        try {
            RetrofitService.instance.like(idNota,idUser)
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
    }

    suspend fun dislike(idNota: Int,idUser: Int){
        try {
            RetrofitService.instance.dislike(idNota,idUser)
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
    }

    suspend fun borrarNota(idNota:Int){
        try {
            RetrofitService.instance.borrarNota(idNota)
        } catch (e: Exception) {
            Log.e("RegistroViewModel", "Error general: ${e.message}")
            e.printStackTrace()
        }
    }
}