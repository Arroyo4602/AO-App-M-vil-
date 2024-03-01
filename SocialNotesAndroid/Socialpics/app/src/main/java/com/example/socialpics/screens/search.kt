package com.example.socialpics.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.socialpics.R
import com.example.socialpics.modelo.ProfileAjeno
import com.example.socialpics.modelo.RegistroViewModel
import com.example.socialpics.modelo.RetrofitService
import com.example.socialpics.modelo.Sesion
import com.example.socialpics.modelo.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@Composable
fun MyApiCliente2(onSuccess: (List<User>) -> Unit, onError: (String) -> Unit) {
    var listUsers by remember { mutableStateOf<List<User>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()
    Log.d("SESIONINNI",Sesion.getSesionUsuario().toString())
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitService.instance.listarUsers(Sesion.getSesionUsuario())
                listUsers = response // Actualiza el estado con la respuesta
                onSuccess(response) // Llama a la función onSuccess
            } catch (e: IOException) {
                // Manejo del error
                error = "Error de conexión: ${e.message}"
                e.printStackTrace()
                onError(error!!)
            } catch (e: HttpException) {
                // Manejo del error HTTP
                error = "Error HTTP: ${e.message}"
                e.printStackTrace()
                onError(error!!)
            } catch (e: Exception) {
                // Manejo de otros errores
                error = "Error desconocido: ${e.message}"
                e.printStackTrace()
                onError(error!!)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun search(navController: NavController){
    var usersLista by remember { mutableStateOf<List<User>?>(null) }
    val registroViewModel: RegistroViewModel = viewModel()
    var error by remember { mutableStateOf<String?>(null) }
    MyApiCliente2(
        onSuccess = { usersLista = it },
        onError = { error = it }
    )
    Log.d("listausers",usersLista.toString())
    val logo= painterResource(R.drawable.socialnotes)
    Scaffold(
        bottomBar = {
            BottomAppBar(
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center

                    ) {

                        // Icono Home
                        BottomNavigationItem(
                            selected = false,
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            icon = {
                                IconButton(onClick = {navController.navigate("home")}) {
                                    Icon(imageVector = Icons.Default.Home, contentDescription ="home", tint = Color.White,modifier = Modifier.size(50.dp) )
                                }
                            },
                        )

                        // Espaciado entre iconos
                        Spacer(modifier = Modifier.weight(1f))

                        // Icono Search
                        BottomNavigationItem(
                            selected = false,
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            icon = {
                                IconButton(onClick = {navController.navigate("search")}) {
                                    Icon(imageVector = Icons.Default.Search, contentDescription ="home", tint = Color.White,modifier = Modifier.size(50.dp) )
                                }
                            },
                        )

                        // Espaciado entre iconos
                        Spacer(modifier = Modifier.weight(1f))

                        // Icono Notifications
                        BottomNavigationItem(
                            selected = false,
                            onClick = {},
                            modifier = Modifier.weight(1f),
                            icon = {
                                IconButton(onClick = {navController.navigate("profile")}) {
                                    Icon(imageVector = Icons.Default.Person, contentDescription ="home", tint = Color.White,modifier = Modifier.size(50.dp) )
                                }
                            },
                        )
                    }
                },
                backgroundColor = Color.Black,
            )
        }
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(0.dp))
            Row {
                Image(modifier= Modifier
                    .height(100.dp)
                    .width(311.dp),
                    painter = logo,
                    contentDescription = "logoPrincipal")
            }
            Spacer(modifier = Modifier.height(0.dp))
            var nombreUser by remember { mutableStateOf("") }
            val updatedNombreUser = rememberUpdatedState(nombreUser)
            Row{
                androidx.compose.material3.OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(255, 255, 255, 255),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(255, 255, 255, 255),
                        unfocusedLabelColor = Color.Gray,
                        textColor = Color.White
                    ),
                    value = updatedNombreUser.value,
                    onValueChange = { nombreUser = it },
                    singleLine = true,
                    label = {
                        Row {
                            if (nombreUser.isEmpty()) {
                                Icon(
                                    imageVector = if (nombreUser.isNotEmpty()) Icons.Default.Search else Icons.Default.Search,
                                    contentDescription = "Email Icon",
                                    tint = if (nombreUser.isNotEmpty()) Color.Black else Color.Gray
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Buscar usuario", style = TextStyle(color = Color.White))
                        }
                    },
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .height(600.dp)
            ){
                item {
                    val listaFiltrada= usersLista?.filter {
                        it.nombreUser.contains(updatedNombreUser.value)
                    }
                    listaFiltrada?.forEach {
                        usersSearch(it,navController)
                    }
                }
            }
        }
    }
}
@Composable
fun usersSearch(u: User,navController: NavController) {
    val registroViewModel: RegistroViewModel = viewModel()
    var text="FOLLOW"

    u.seguidores?.let { seguidores ->
        Log.d("TEXTAZO",text)
        if (seguidores.any { it.idFollow == Sesion.getSesionUsuario() }) {
            text = "UNFOLLOW"
        }
    }

    Log.d("TEXTAZO",text)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .border(BorderStroke(width = 1.dp, color = Color.White))
        ) {
            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = {
                    ProfileAjeno.setSesionUsuario(u.idUser)
                    navController.navigate("profile_ajeno")
                }
            ) {
                Text(text = "${u.nombreUser}", color = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Black),
                onClick = {
                    registroViewModel.viewModelScope.launch {
                        if (text.equals("FOLLOW")){
                            registroViewModel.follow(Sesion.getSesionUsuario(),u.idUser)
                        }
                        if (text.equals("UNFOLLOW")){
                            registroViewModel.unfollow(Sesion.getSesionUsuario(),u.idUser)
                        }
                        delay(500)
                        navController.navigate("search")
                    }
                }
            ) {
                Text(text = text, color = Color.White)
            }
        }
    }
}