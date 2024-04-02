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

    fun deleteOne(
        songID: String = "mkrJjKll0EuThsRl9Voc",
        context: Context,
        navController: NavController,
        backToHome: () -> Unit
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