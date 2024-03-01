package com.example.socialpics.navigation

open class AppScreens(val ruta:String) {
    object login:AppScreens("login")
    object registro:AppScreens("registro")
    object home:AppScreens("home")
    object search:AppScreens("search")
    object profile:AppScreens("profile")
    object newpost:AppScreens("newpost")
    object profile_ajeno:AppScreens("profile_ajeno")
}