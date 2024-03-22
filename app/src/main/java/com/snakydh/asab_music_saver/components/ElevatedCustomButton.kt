package com.snakydh.asab_music_saver.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Composable
fun ElevatedCustomButton(text: String, onClick: () -> Unit) {
    ElevatedButton(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
    }
}