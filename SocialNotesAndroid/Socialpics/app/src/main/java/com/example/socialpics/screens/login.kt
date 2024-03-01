package com.example.socialpics.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.socialpics.R
import com.example.socialpics.modelo.RegistroViewModel
import com.example.socialpics.modelo.Sesion
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun login(navController: NavController){
    val image1 = painterResource(R.drawable.visibilitywhite)
    val image2 = painterResource(R.drawable.visibilityoffwhite)
    val logo= painterResource(R.drawable.socialnotes)
    val showDialog = remember { mutableStateOf(false) }
    val registroViewModel: RegistroViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(0.dp))
        Row {
            Image(modifier=Modifier
                .height(201.dp)
                .width(311.dp),
                    painter = logo,
                contentDescription = "logoPrincipal")
        }
        Text(text = "Inicia sesión con tu cuenta", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text ="Bienvenido de nuevo", fontSize = 20.sp, color = Color.White)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )
            Text("o continua con tu email", color = Color.White)
            Divider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        var nombreUser by rememberSaveable { mutableStateOf("") }
        val updatenombreUser = rememberUpdatedState(nombreUser)
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

            value = updatenombreUser.value,
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
        Spacer(modifier = Modifier.size(10.dp))

        var password by rememberSaveable { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }
        var isPasswordFocused by remember { mutableStateOf(false) }
        val updateContraseña = rememberUpdatedState(password)
        val iconSize = 20.dp
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(4, 104, 249, 255),
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = Color(4, 104, 249, 255),
                unfocusedLabelColor = Color.Gray,
                textColor=Color.White
            ),
            // Utiliza un nombre más descriptivo para la variable 'updateContraseña'
            value = updateContraseña.value,
            onValueChange = {
                // Asigna el nuevo valor directamente a 'password'
                password = it
            },
            singleLine = true,
            label = {
                Row {
                    if (password.isEmpty()) {
                        Icon(
                            imageVector = if (password.isNotEmpty()) Icons.Default.Lock else Icons.Default.Lock,
                            contentDescription = "Lock Icon",
                            tint = if (password.isNotEmpty()) Color.Black else Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Contraseña",style = TextStyle(color = Color.White))
                }
            },
            visualTransformation = if (isPasswordVisible) {
                // Usa VisualTransformation.None para mostrar el texto de la contraseña si es visible
                VisualTransformation.None
            } else {
                // Usa PasswordVisualTransformation para ocultar el texto de la contraseña si no es visible
                PasswordVisualTransformation()
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        // Invierte el estado de isPasswordVisible al hacer clic en el icono
                        isPasswordVisible = !isPasswordVisible
                    },
                ) {
                    Image(
                        // Utiliza operador ternario para seleccionar la imagen en función de isPasswordVisible
                        if (isPasswordVisible) image2 else image1,
                        // Utiliza operador ternario para seleccionar la descripción de la imagen
                        contentDescription = if (isPasswordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                        // Utiliza el modificador size directamente en Image
                        modifier = Modifier.size(iconSize)
                    )
                }
            },
            modifier = Modifier
                .padding(10.dp, 0.dp, 10.dp, 0.dp)
                .onFocusChanged { isPasswordFocused = it.isFocused }
        )
        Spacer(modifier = Modifier
            .size(10.dp)
            .padding(10.dp, 0.dp, 10.dp, 0.dp),
        )
        RememberMeCheckbox()
        Button(
            onClick = {
                /*    val response = viewModel.*/
                Log.d("UPDATEDPUTA",updatenombreUser.value)
                Log.d("UPDATEDPUTA",updateContraseña.value)

                registroViewModel.viewModelScope.launch {
                    var response=registroViewModel.log(updatenombreUser.value,updateContraseña.value)
                    if (response.equals("incorrectas")){
                        showDialog.value=true
                    }else{
                        val idSesion=response.toInt()
                        Sesion.setSesionUsuario(idSesion)
                        navController.navigate("home")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(66.dp, 10.dp, 66.dp, 0.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(Color.White),
        ) {
            Text(
                text = "LOG IN",
                fontSize = 20.sp,
                color = Color.Black
            )
        }
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    showDialog.value=false
                },
                title={
                    Text(text = "Credenciales incorrectas",color=Color.Black)
                },
                text = {
                    Text(text = "Por favor, verifica los campos introducidos",color=Color.Black)
                },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(59, 64, 72, 255)),
                        modifier = Modifier
                            .fillMaxWidth(),
                        onClick = {
                            showDialog.value=false
                            navController.navigate("login")
                        }
                    ) {
                        Text(text = "Aceptar")
                    }
                }
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        ClickableLogin("¿No tienes cuenta?", "Regístrate") {
            navController.navigate("registro")
        }

    }
}

@Composable
fun ClickableLogin(text1: String, text2: String, onClick: () -> Unit) {
    Row{
        Text(
            text = text1,
            fontSize = 20.sp,
            color = Color.White,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text2,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(4, 104, 249, 255),
            modifier = Modifier
                .clickable { onClick.invoke() }
        )
    }
}

@Composable
fun RememberMeCheckbox() {
    var rememberMeState by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.toggleable(
            value = rememberMeState,
            onValueChange = { isChecked -> rememberMeState = isChecked }
        ),
    ) {
        Checkbox(
            checked = rememberMeState,
            onCheckedChange = null, // Dejamos esto como nulo ya que ya manejamos el cambio en la fila
            colors = CheckboxDefaults.colors(
                checkedColor = Color(105, 96, 0, 255),
            )
        )
        Text(
            text = "Recuérdame",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White, // Color del texto
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
