package com.example.socialpics.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.socialpics.R
import com.example.socialpics.modelo.RegistroViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun registro(navController: NavController){
    val image1 = painterResource(R.drawable.visibilitywhite)
    val image2 = painterResource(R.drawable.visibilityoffwhite)
    val context = LocalContext.current
    var capturedImageUri by remember { mutableStateOf<Uri>(Uri.EMPTY) }
    val registroViewModel:RegistroViewModel= viewModel()
    var imagen:Uri? by remember { mutableStateOf<Uri?>(null) }
    val showDialog = remember { mutableStateOf(false) }
    val showDialog2 = remember { mutableStateOf(false) }
    val showDialog3 = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var nombreUser by remember { mutableStateOf("") }
        val updatedNombreUser = rememberUpdatedState(nombreUser)
        var email by remember { mutableStateOf("") }
        val updatedEmail = rememberUpdatedState(email)
        var pass by remember { mutableStateOf("") }
        val updatedPass = rememberUpdatedState(pass)

        var pass2 by remember { mutableStateOf("") }
        val updatedPass2 = rememberUpdatedState(pass2)

        var profilePic by remember { mutableStateOf("") }
        val updatedProfilePic = rememberUpdatedState(profilePic)
        var isPasswordVisible by remember { mutableStateOf(false) }
        var isPasswordFocused by remember { mutableStateOf(false) }
        val logo= painterResource(R.drawable.socialnotes)

        val iconSize = 20.dp

        Row {
            Image(modifier= Modifier
                .height(201.dp)
                .width(311.dp),
                painter = logo,
                contentDescription = "logoPrincipal")
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row{
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(4, 104, 249, 255),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(4, 104, 249, 255),
                    unfocusedLabelColor = Color.Gray,
                    textColor=Color.White
                ),
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                ,

                value = updatedNombreUser.value,
                onValueChange = { nombreUser = it },
                singleLine = true,
                label = {
                    Row {
                        if (nombreUser.isEmpty()) {
                            Icon(
                                imageVector = if (nombreUser.isNotEmpty()) Icons.Default.Person else Icons.Default.Person,
                                contentDescription = "Email Icon",
                                tint = if (nombreUser.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Nombre de usuario", style = TextStyle(color = Color.White))
                    }
                },
            )
        }
        Row{
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(4, 104, 249, 255),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(4, 104, 249, 255),
                    unfocusedLabelColor = Color.Gray,
                    textColor=Color.White
                ),
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                ,

                value = updatedEmail.value,
                onValueChange = { email = it },
                singleLine = true,
                label = {
                    Row {
                        if (email.isEmpty()) {
                            Icon(
                                imageVector = if (email.isNotEmpty()) Icons.Default.Email else Icons.Default.Email,
                                contentDescription = "Email Icon",
                                tint = if (email.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Email", style = TextStyle(color = Color.White))
                    }
                },
            )
        }
        Row{
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(4, 104, 249, 255),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(4, 104, 249, 255),
                    unfocusedLabelColor = Color.Gray,
                    textColor=Color.White
                ),
                value = updatedPass.value,
                onValueChange = {
                    pass = it
                },
                singleLine = true,
                label = {
                    Row {
                        if (pass.isEmpty()) {
                            Icon(
                                imageVector = if (pass.isNotEmpty()) Icons.Default.Lock else Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = if (pass.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Contraseña",style = TextStyle(color = Color.White))
                    }
                },
                visualTransformation = if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        },
                    ) {
                        Image(
                            if (isPasswordVisible) image2 else image1,
                            contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            modifier = Modifier.size(iconSize)
                        )
                    }
                },
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    .onFocusChanged { isPasswordFocused = it.isFocused }
            )
        }
        Row{
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(4, 104, 249, 255),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(4, 104, 249, 255),
                    unfocusedLabelColor = Color.Gray,
                    textColor=Color.White
                ),
                value = updatedPass2.value,
                onValueChange = {
                    pass2 = it
                },
                singleLine = true,
                label = {
                    Row {
                        if (pass.isEmpty()) {
                            Icon(
                                imageVector = if (pass.isNotEmpty()) Icons.Default.Lock else Icons.Default.Lock,
                                contentDescription = "Lock Icon",
                                tint = if (pass.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Contraseña",style = TextStyle(color = Color.White))
                    }
                },
                visualTransformation = if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        },
                    ) {
                        Image(
                            if (isPasswordVisible) image2 else image1,
                            contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                            modifier = Modifier.size(iconSize)
                        )
                    }
                },
                modifier = Modifier
                    .padding(10.dp, 0.dp, 10.dp, 0.dp)
                    .onFocusChanged { isPasswordFocused = it.isFocused }
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        Row {
            if (showDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog.value=false
                    },
                    title={
                        Text(text = "Formulario no válido",color=Color.Black)
                    },
                    text = {
                        Text(text = "Por favor, verifica que todos los campos estén llenos y sean válidos.",color=Color.Black)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(Color.Black),
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                showDialog.value=false
                            }
                        ) {
                            Text(text = "Aceptar")
                        }
                    }
                )
            }
            if (showDialog2.value) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog2.value=false
                    },
                    title={
                        Text(text = "Formulario no válido",color=Color.Black)
                    },
                    text = {
                        Text(text = "Las contraseñas no coinciden.",color=Color.Black)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(Color.Black),
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                showDialog.value=false
                            }
                        ) {
                            Text(text = "Aceptar")
                        }
                    }
                )
            }
            if (showDialog3.value) {
                AlertDialog(
                    onDismissRequest = {
                        showDialog3.value=false

                    },
                    title={
                        Text(text = "Formulario no válido",color=Color.Black)
                    },
                    text = {
                        Text(text = "El nombre de usuario ya está cogido, pruebe con otro distinto.",color=Color.Black)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(Color.Black),
                            modifier = Modifier
                                .fillMaxWidth(),
                            onClick = {
                                showDialog3.value=false
                            }
                        ) {
                            Text(text = "Aceptar")
                        }
                    }
                )
            }
            Button(
               
                onClick = {
                    var passvalid=comprobarPass(updatedPass.value,updatedPass2.value)
                    if (passvalid){
                        var validez=isFormValid(updatedNombreUser.value,updatedEmail.value,updatedPass.value)
                        if(validez){
                            registroViewModel.viewModelScope.launch {
                               var mensaje=registroViewModel.registrar(updatedNombreUser.value,updatedEmail.value,updatedPass.value)
                                if(mensaje.equals("repetido")){
                                    showDialog3.value=true
                                }else{
                                    navController.navigate("login")
                                }

                            }
                        }else{
                            showDialog.value=true
                        }
                    }else{
                        showDialog2.value=true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(66.dp, 10.dp, 66.dp, 0.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.White),
            ) {
                Text(
                    text = "Registrarse",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }
    }
}
fun isFormValid(userName:String,email:String,pass:String): Boolean{
    if (tieneMayusculaPrimeraPosicion(userName) && contieneSoloLetrasONumeros(userName) && userName.length>6 && userName.length<20 && contieneArrobaYPunto(email) && pass.length>6 && pass.length<14){
        return true
    }else{
        return false
    }
}
fun tieneMayusculaPrimeraPosicion(input: String): Boolean {
    val regex = Regex("^[A-Z].*")
    return regex.matches(input)
}

fun contieneSoloLetras(input: String): Boolean {
    val regex = Regex("^[a-zA-Zá-úÁ-Ú ]+\$")
    return regex.matches(input)
}

fun contieneSoloLetrasONumeros(input: String): Boolean {
    val regex = Regex("^[a-zA-Z0-9á-úÁ-Ú ]+\$")
    return regex.matches(input)
}
fun contieneArrobaYPunto(input: String): Boolean {
    val regex = Regex(".*@.+\\..+")
    return regex.matches(input)
}

fun comprobarPass(pass1:String,pass2: String):Boolean{
    if (pass1.equals(pass2)){
        return true
    }else{
        return false
    }
}
