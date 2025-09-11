package com.diegocal.laboratorio6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.diegocal.laboratorio6.ui.theme.Laboratorio6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio6Theme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {
                    // Aquí definiremos las pantallas
                    composable("home") {
                        // Esta será la pantalla principal de la galería
                        // PexelsScreen(navController = navController)
                    }
                    composable("profile") {
                        // Pantalla de perfil
                        // ProfileScreen()
                    }
                    composable(
                        "details/{photoId}",
                        arguments = listOf(navArgument("photoId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val photoId = backStackEntry.arguments?.getInt("photoId")
                        // Pantalla de detalles
                        // DetailsScreen(photoId = photoId)
                    }
                }
            }
        }
    }
}