package com.example.socialpics.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.socialpics.modelo.RegistroViewModel
import com.example.socialpics.modelo.RetrofitService
import com.example.socialpics.screens.home
import com.example.socialpics.screens.login
import com.example.socialpics.screens.newpost
import com.example.socialpics.screens.profile
import com.example.socialpics.screens.profileajeno
import com.example.socialpics.screens.registro
import com.example.socialpics.screens.search

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){
    val navigationController= rememberNavController()
    NavHost(navController = navigationController, startDestination = AppScreens.login.ruta){
        composable(AppScreens.login.ruta){login(navigationController)}
        composable(AppScreens.registro.ruta){ registro(navigationController)}
        composable(AppScreens.home.ruta){ home(navigationController)}
        composable(AppScreens.profile.ruta){ profile(navController = navigationController)}
        composable(AppScreens.search.ruta){ search(navController = navigationController)}
        composable(AppScreens.newpost.ruta){ newpost(navController = navigationController) }
        composable(AppScreens.profile_ajeno.ruta){ profileajeno(navController = navigationController) }
    }
}