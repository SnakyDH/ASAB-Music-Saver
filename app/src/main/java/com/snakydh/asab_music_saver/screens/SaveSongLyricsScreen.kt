package com.snakydh.asab_music_saver.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.snakydh.asab_music_saver.navigation.AppScreens
import com.snakydh.asab_music_saver.viewModel.SongViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SaveSongLyricsScreen(navController: NavController, viewModel: SongViewModel = SongViewModel(),context: Context) {
    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ), title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                    }, imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "arrow back"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Save Song Lyrics")
            }
        })
    }) { innerPadding ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Form(navController, viewModel,context)
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }
    }
}

@Composable
fun Form(navController: NavController, viewModel: SongViewModel,context: Context) {
    val name = remember { mutableStateOf("") }
    val lyrics = remember { mutableStateOf("") }
    Text(
        modifier = Modifier
            .padding(0.dp, 20.dp),
        text = "Guardar letra de la Canción",
        fontSize = 35.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 40.sp
    )
    Column(
        modifier = Modifier

            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ingrese el nombre de la Canción: ",
            modifier = Modifier.padding(10.dp)
        )
        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(text = "Nombre") },
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Ingrese la letra de la Canción: ",
            modifier = Modifier.padding(10.dp)
        )
        OutlinedTextField(
            value = lyrics.value,
            onValueChange = { lyrics.value = it },
            label = { Text(text = "Letra") },
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(295.dp)
                .padding(horizontal = 35.dp)
        )
        Spacer(
            modifier = Modifier.height(20.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = {
                viewModel.saveSong(title = name.value, lyrics = lyrics.value, context = context)
                navController.navigate(AppScreens.HomeScreen.route)
            }) {
                Text("Guardar", fontSize = 24.sp)
            }
            OutlinedButton(onClick = { navController.navigate(AppScreens.HomeScreen.route) }) {
                Text(text = "Atrás", fontSize = 24.sp)
            }
        }
    }
}
