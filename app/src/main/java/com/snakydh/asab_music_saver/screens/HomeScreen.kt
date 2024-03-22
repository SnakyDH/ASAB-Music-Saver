package com.snakydh.asab_music_saver.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.snakydh.asab_music_saver.navigation.AppScreens
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("ASAB - Music Saver")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(0.4F),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ElevatedButton(
                    onClick = { navController.navigate(route = AppScreens.SaveSongLyricsScreen.route) }) {
                    Text(text = "Guardar Letra")
                }
                ElevatedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Practicar Maraca")
                }
                ElevatedButton(onClick = { /*TODO*/ }) {
                    Text(text = "Practicar Tambor")
                }
            }
            Card(
                modifier = Modifier
                    .padding(top = 40.dp)
                    .clip(shape = RoundedCornerShape(10, 10)),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 5.dp
                )
            ) {
                SongsList()
            }
        }
    }
}

@Composable
fun SongsList() {
    val padding = 10.dp;
    Column(
        modifier = Modifier
            .padding(padding, 0.dp)
            .fillMaxHeight()
    )
    {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Canciones",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.padding(padding))
        SongCard()
        Spacer(modifier = Modifier.padding(padding))
        SongCard()
    }
}

@Composable
fun SongCard() {
    Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(20.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(20))
        ) {
            Icon(
                Icons.Default.Star,
                modifier = Modifier
                    .align(
                        Alignment.CenterVertically
                    )
                    .size(40.dp),
                contentDescription = "Song icons"
            )
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .clip(shape = RoundedCornerShape(10)),
            ) {
                Text(
                    text = "Nombre de Cancion",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Text(
                    text = Date().toString(),
                    fontSize = 16.sp,
                )
            }
        }
    }

}