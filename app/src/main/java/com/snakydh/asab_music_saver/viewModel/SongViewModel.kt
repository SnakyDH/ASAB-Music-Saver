package com.snakydh.asab_music_saver.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.snakydh.asab_music_saver.model.Song
import com.snakydh.asab_music_saver.repository.SongRepository

class SongViewModel : ViewModel() {
    private val _songRepository = SongRepository()
    fun saveSong(title: String, lyrics: String) {
        val song = Song(title, lyrics)
        _songRepository.save(song)
    }
}