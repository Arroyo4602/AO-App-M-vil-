package com.example.socialpics

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import com.example.socialpics.navigation.AppNavigation
import com.example.socialpics.ui.theme.SocialpicsTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SocialpicsTheme {
                Surface{
                    AppNavigation()
                }
            }
        }

    }
}

