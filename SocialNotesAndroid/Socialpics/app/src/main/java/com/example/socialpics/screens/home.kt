package com.example.socialpics.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.socialpics.R
import com.example.socialpics.modelo.Nota
import com.example.socialpics.modelo.RegistroViewModel
import com.example.socialpics.modelo.RetrofitService
import com.example.socialpics.modelo.Sesion
import com.example.socialpics.modelo.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

@Composable
fun MyApiCliente3(onSuccess: (List<Nota>) -> Unit, onError: (String) -> Unit) {
    var notasLista by remember { mutableStateOf<List<Nota>?>(null) }
    var error by remember { mutableStateOf<String?>(null) }
    val coroutineScope = rememberCoroutineScope()

    Log.d("SESIONINNI",Sesion.getSesionUsuario().toString())
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            try {
                val response = RetrofitService.instance.listar(Sesion.getSesionUsuario())
                notasLista = response // Actualiza el estado con la respuesta
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
fun home(navController: NavController){
    val logo= painterResource(R.drawable.socialnotes)
    var notasLista by remember { mutableStateOf<List<Nota>?>(null) }
    val registroViewModel: RegistroViewModel = viewModel()
    var error by remember { mutableStateOf<String?>(null) }

    MyApiCliente3(
        onSuccess = { notasLista = it },
        onError = { error = it }
    )

    Log.d("NOTASLISTA",notasLista.toString())

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

            Row {
                IconButton(
                    modifier=Modifier
                        .padding(start = 250.dp),
                    onClick = {
                        navController.navigate("newpost")
                    }
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription ="add",tint=Color.White, modifier = Modifier.size(500.dp) )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .height(600.dp)
            ){
                item {
                    notasLista?.forEach {nota->
                        notasDetailshome(nota,navController)
                    }
                }
            }
        }
    }
}


@Composable
fun notasDetailshome(n:Nota,navController: NavController){
    var user by remember { mutableStateOf<User?>(null)}
    val registroViewModel:RegistroViewModel= viewModel()

    registroViewModel.viewModelScope.launch {
        user=registroViewModel.getUser(n.idUser)
    }
    var likes:Int?

    if (n.likes?.size==null){
        likes=0
    }else{
        likes=n.likes.size
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(39.dp, 30.dp, 39.dp, 30.dp)
            .background(Color(253, 227, 131, 255)),
    ){
        Row (
            modifier=Modifier
                .padding(top=20.dp)
        ){

            Text(text = "@${user?.nombreUser}",modifier=Modifier.padding(start = 20.dp),fontSize = 16.sp,fontWeight = FontWeight.W900, fontFamily = FontFamily.Serif)
            Spacer(modifier = Modifier.width(155.dp))
            Icon(imageVector = Icons.Default.PushPin, contentDescription ="likebutton",tint=Color.Black,modifier = Modifier.size(30.dp))
        }
        Row {
            Text(text = "${n.mensaje}",fontSize = 16.sp,modifier=Modifier.padding(start = 20.dp,top=15.dp),fontWeight = FontWeight.W900, fontFamily = FontFamily.Serif)
        }
        Row {
            Text(text = "${likes} likes",fontSize = 16.sp,modifier=Modifier.padding(start = 20.dp,top=15.dp),fontWeight = FontWeight.W900, fontFamily = FontFamily.Serif)
            Spacer(modifier = Modifier.width(190.dp))
            var likeColor=Color.White
            var likesaux=n.likes
            likesaux.forEach {
                if(it.idUser ==Sesion.getSesionUsuario()){
                    likeColor=Color.Red
                }
            }

            IconButton(
                modifier = Modifier
                    .padding(top=0.dp),
                onClick = {

                    if (likeColor==Color.White){
                        registroViewModel.viewModelScope.launch {
                            registroViewModel.like(n.idnota,Sesion.getSesionUsuario())
                            delay(200)
                        }
                        navController.navigate("home")
                    }
                    if (likeColor==Color.Red){
                        registroViewModel.viewModelScope.launch {
                            registroViewModel.dislike(n.idnota,Sesion.getSesionUsuario())
                            delay(200)
                        }
                        navController.navigate("home")
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription ="likebutton",tint=likeColor,modifier = Modifier.size(30.dp))
            }
        }
    }
}