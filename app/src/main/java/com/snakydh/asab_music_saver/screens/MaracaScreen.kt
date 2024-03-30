package com.snakydh.asab_music_saver.screens

import android.content.Context
import android.content.Intent
import android.hardware.SensorEvent
import android.hardware.SensorListener
import android.media.MediaPlayer
import android.widget.TextView
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.snakydh.asab_music_saver.MainActivity
import com.snakydh.asab_music_saver.MaracaActivity
import com.snakydh.asab_music_saver.R
import com.snakydh.asab_music_saver.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaracaScreen(context: Context) {
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
                                context.startActivity(Intent(context, MainActivity::class.java))
                            },
                            imageVector = Icons.Default.ArrowBack, contentDescription = "arrow back"
                        )
                        Text(" Maraca Screen")

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
            MaracaImage()

            Text(
                text = "< < Shake your Device > >",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun MaracaImage() {
    Image(
        modifier = Modifier
            .width(500.dp)
            .height(500.dp),
        alignment = Alignment.Center,
        painter = painterResource(id = R.drawable.mexcat),
        contentDescription = "maraca cat",
    )
}