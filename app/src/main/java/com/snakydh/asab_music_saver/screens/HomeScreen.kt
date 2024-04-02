package com.snakydh.asab_music_saver.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.snakydh.asab_music_saver.components.ElevatedCustomButton
import com.snakydh.asab_music_saver.model.Song
import com.snakydh.asab_music_saver.navigation.AppScreens
import com.snakydh.asab_music_saver.viewModel.SongViewModel
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, context: Context, songViewModel: SongViewModel) {

    var searchWord: String by remember { mutableStateOf("") }
    var deleteId: String by remember { mutableStateOf("") }
    var songs: MutableList<Song> by remember { mutableStateOf(mutableListOf()) }
    var songSearched: Song by remember {
        mutableStateOf(Song())
    }
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
            MenuButtons(navController)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    shape = CircleShape,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "search")
                    },
                    placeholder = {
                        Text(text = "Search")
                    },
                    value = searchWord,
                    onValueChange = { searchWord = it }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4F),
                    onClick = {
                        songViewModel.getAll(
                            context
                        ) {
                            songs = it
                        }
                        songViewModel.getOneByTitle(
                            titleToSearch = searchWord,
                            context = context
                        ) { data ->
                            songs = mutableListOf(data)
                        }
                    }) {
                    Text(text = "Search", textAlign = TextAlign.Center)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp, horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,

                ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F),
                    shape = CircleShape,
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    leadingIcon = {
                        Icon(Icons.Default.Delete, contentDescription = "delete")
                    },
                    placeholder = {
                        Text(text = "Delete")
                    },
                    value = deleteId,
                    onValueChange = { deleteId = it }
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.4F),
                    onClick = {
                        songViewModel.deleteOne(
                            deleteId,
                            context
                        )
                        songViewModel.getOneByTitle(
                            titleToSearch = deleteId,
                            context = context
                        ) { data ->
                            songs = mutableListOf(data)
                        }
                    }) {
                    Text(text = "delete", textAlign = TextAlign.Center)
                }
            }
            SongsList(navController, context, songs, songViewModel)
        }
    }
}

@Composable
fun MenuButtons(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.4F)
            .padding(horizontal = 100.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedCustomButton(
            text = "Guardar\nLetra",
            onClick = { navController.navigate(AppScreens.SaveSongLyricsScreen.route) },
        )
        ElevatedCustomButton(
            text = "Practicar\nMaraca",
            onClick = { navController.navigate(AppScreens.MaracaInstructionScreen.route) },
        )
        ElevatedCustomButton(
            text = "Practicar\nTambor",
            onClick = { navController.navigate(AppScreens.DrumInstructionScreen.route) },
        )
    }
}

@Composable
fun SongsList(
    navController: NavController,
    context: Context,
    songs: MutableList<Song>,
    songViewModel: SongViewModel
) {
    val padding = 10.dp
    val scrollState = rememberScrollState()
    Card(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10, 10)),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        )
    ) {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = padding)
                .fillMaxSize()
        )
        {
            if (songs.isNotEmpty()) {
                Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(20.dp)) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        text = "Canciones",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            } else {
                Card(modifier = Modifier.fillMaxSize()) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        text = "Cargue las canciones",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 32.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
            for (song in songs) {
                SongCard(
                    navController,
                    song = song,
                    songViewModel = songViewModel,
                    context = context
                )
            }
        }
    }
}

@Composable
fun SongCard(
    navController: NavController,
    songViewModel: SongViewModel,
    context: Context,
    song: Song
) {
   // var songSelected: Song by remember { mutableStateOf(Song()) }
    Button(onClick = {
        songViewModel.getOneById(songId = song.id, context = context) {
           // songSelected = it
        }


        // todo: Explain
        navController.navigate(AppScreens.SongDetailScreen.route)
    }, shape = RoundedCornerShape(20.dp)) {
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
                    .padding(10.dp)
                    .clip(shape = RoundedCornerShape(10)),
            ) {
                Text(
                    text = song.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
                Text(
                    text = song.id,
                    fontSize = 16.sp,
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(5.dp))
}