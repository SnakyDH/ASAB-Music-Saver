package com.snakydh.asab_music_saver.screens

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.snakydh.asab_music_saver.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrumInstructionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.clickable {
                                navController.popBackStack()
                            },
                            imageVector = Icons.Default.ArrowBack, contentDescription = "arrow back"
                        )
                        Text(" Instrucciones Tambor")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp, vertical = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(Color.White),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = "¡Bienvenido/a a la función de práctica de tambor de nuestra aplicación!\n",
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Sigue estos simples pasos para comenzar a practicar:\n" +
                                "Con nuestra función de práctica de tambor, simplemente presiona el botón de empezar y sumérgete en la emoción " +
                                "de tocar un tambor virtual. Una vez dentro, al tocar el parche del tambor en la pantalla, desencadenarás " +
                                "el sonido de un tambor. \n",
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "¡Eso es todo! Ahora estás listo/a para comenzar a practicar el tambor y disfrutar de la experiencia musical " +
                                "que ofrece nuestra aplicación. ¡Diviértete tocando!",
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )

                }
            }
            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                FilledTonalButton(onClick = { navController.navigate(AppScreens.DrumScreen.route) }) {
                    Text("¡Comenzar!", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(15.dp))

                OutlinedButton(onClick = { navController.navigate(AppScreens.HomeScreen.route) }) {
                    Text(text = "Atrás", fontSize = 24.sp)
                }

            } // Column inicial
        }
    }
}
