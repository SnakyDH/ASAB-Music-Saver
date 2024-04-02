package com.snakydh.asab_music_saver.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.snakydh.asab_music_saver.model.Song
import com.snakydh.asab_music_saver.repository.SongRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class SongViewModel : ViewModel() {
    private val _songRepository = SongRepository()
    fun saveSong(title: String, lyrics: String) {
        val song = Song(title = title, lyrics = lyrics)
        _songRepository.save(song)
    }

    fun getOneById(
        songId: String = "KcbqqaNbkxVv97BdvzAr",
        context: Context,
        data: (Song) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("songs")
            .document(songId)
        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        val songData = it.toObject<Song>()!!
                        data(songData)
                    } else {
                        Toast.makeText(context, "No Song", Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun getOneByTitle(
        titleToSearch: String = "amorcito",
        context: Context,
        data: (Song) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("songs")
            .whereEqualTo("title", titleToSearch)
        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    for (document in it) {
                        Log.d(
                            "Song View Model",
                            "Document Title ${document.getString("title")} "
                        )
                        val songId = document.id
                        val title = document.getString("title")
                        val lyrics = document.getString("lyrics")
                        val song = Song(songId, title ?: "", lyrics ?: "")
                        data(song)
                        if (title == null || lyrics == null) {
                            Log.w(
                                "Song View Model - get by title",
                                "Document ${document.id} is missing required fields (title or lyrics). Skipping conversion."
                            )
                            continue
                        }
                    }
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }
    fun getAll(
        context: Context,
        data: (MutableList<Song>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val songs = mutableListOf<Song>()

        val fireStoreRef = Firebase.firestore
            .collection("songs")
        try {
            fireStoreRef.get()
                .addOnSuccessListener {
                    for (document in it) {
                        Log.d(
                            "Song View Model",
                            "Document Title ${document.getString("title")} "
                        )
                        val songId = document.id
                        val title = document.getString("title")
                        val lyrics = document.getString("lyrics")
                        val song = Song(songId, title ?: "", lyrics ?: "")
                        songs.add(song)
                        if (title == null || lyrics == null) {
                            Log.w(
                                "Song View Model - get all",
                                " ${document.id}"
                            )
                            continue
                        }
                    }
                    data(songs)
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun deleteOne(
        songID: String = "mkrJjKll0EuThsRl9Voc",
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val fireStoreRef = Firebase.firestore
            .collection("songs")
            .document(songID)
        try {
            fireStoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Deleted Song", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


}