package com.example.socialpics.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
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
fun newpost(navController: NavController){
    val registroViewModel: RegistroViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row {
            val logo= painterResource(R.drawable.socialnotes)
            Image(modifier= Modifier
                .height(201.dp)
                .width(311.dp),
                painter = logo,
                contentDescription = "logoPrincipal")
        }
        Spacer(modifier = Modifier.height(40.dp))
        var note by remember { mutableStateOf("") }
        val updatedNote = rememberUpdatedState(note)
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

                value = updatedNote.value,
                onValueChange = { note = it },
                label = {
                    Row {
                        if (note.isEmpty()) {
                            Icon(
                                imageVector = if (note.isNotEmpty()) Icons.Default.Create else Icons.Default.Create,
                                contentDescription = "Email Icon",
                                tint = if (note.isNotEmpty()) Color.Black else Color.Gray
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Añadir nota", style = TextStyle(color = Color.White))
                    }
                },
            )
        }
        Row {
            Button(
                onClick = {
                    registroViewModel.viewModelScope.launch {
                        registroViewModel.postnote(updatedNote.value,Sesion.getSesionUsuario())
                        navController.navigate("home")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(66.dp, 10.dp, 66.dp, 0.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.White),
            )
            {
                Text(
                    text = "Post",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            }
        }

    }
}
