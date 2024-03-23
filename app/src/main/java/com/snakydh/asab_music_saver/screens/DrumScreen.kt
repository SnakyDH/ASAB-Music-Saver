package com.snakydh.asab_music_saver.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.snakydh.asab_music_saver.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrumScreen(navController: NavController) {
    Scaffold(topBar = {
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
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Drum")
                }
            }
        )
    }

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            DrumImage()
            Text(
                text = "< < Press Drum > >",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Drum()
        }
    }
}

@Composable
fun DrumImage() {
    Card(
        modifier = Modifier.padding(20.dp),
        shape = CircleShape,
    ) {
        Image(
            alignment = Alignment.Center,
            painter = painterResource(id = R.drawable.bongocat),
            contentDescription = "bongo cat",
        )
    }
}

@Composable
fun Drum() {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .clip(CircleShape),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF703F1E)
            ),
        ) {
            Button(
                modifier = Modifier
                    .fillMaxSize(0.7F)
                    .padding(20.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFeadcd1)
                ),
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    imageVector = Icons.Default.Star, contentDescription = "bongo",
                    tint = Color(0xFF703F1E)
                )
            }
        }
    }
}
