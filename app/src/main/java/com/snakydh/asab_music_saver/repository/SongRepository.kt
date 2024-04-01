package com.snakydh.asab_music_saver.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.snakydh.asab_music_saver.model.Song

class SongRepository {
    fun save(song: Song) {
        FirebaseFirestore.getInstance()
            .collection("songs")
            .add(
            song.toMap()
        ).addOnSuccessListener {
            Log.d("ASAB Firebase", "Song created ${it.id}")
        }.addOnFailureListener {
            Log.d("ASAB Firebase", "Error $it")
        }
    }
}