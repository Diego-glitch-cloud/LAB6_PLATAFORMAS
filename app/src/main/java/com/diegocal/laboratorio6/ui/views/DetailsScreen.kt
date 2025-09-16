package com.diegocal.laboratorio6.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.diegocal.laboratorio6.Photo
import com.diegocal.laboratorio6.RetrofitPexels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    photoId: Int
) {
    var photo by remember { mutableStateOf<Photo?>(null) }
    var isLoading by remember { mutableStateOf(true) }


    LaunchedEffect(photoId) {
        isLoading = true
        // Esto es solo un placeholder, la API de Pexels no tiene un endpoint para esto.
        // Para el laboratorio, podrías buscar la foto en la lista de la pantalla principal.
        // Por ahora, para que compile y funcione la navegación,
        // dejaremos un mensaje simple.
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (isLoading) "Cargando..." else photo?.photographer ?: "Detalles"
                    )
                },
                navigationIcon = {
                    // El botón de volver está en MainActivity.kt con navController.
                    // Para este composable, no necesitamos el navController.
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                photo?.let { p ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Muestra la imagen de la foto
                        AsyncImage(
                            model = p.imageUrl.large,
                            contentDescription = p.photographer,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        // Muestra el nombre del fotógrafo
                        Text(
                            text = "Fotógrafo: ${p.photographer}",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        // Muestra el tamaño de la foto (o likes, si tuvieras la info)
                        Text(
                            text = "Tamaño: ${p.width} x ${p.height}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        // Botón opcional de compartir (sin funcionalidad por ahora)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { /* TODO: Implementar share intent */ }) {
                            Text("Compartir")
                        }
                    }
                }
            }
        }
    }
}