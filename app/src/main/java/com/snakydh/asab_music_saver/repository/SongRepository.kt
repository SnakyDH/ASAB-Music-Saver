package com.snakydh.asab_music_saver.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.Firebase
import com.snakydh.asab_music_saver.model.Song
import kotlinx.coroutines.tasks.await

class SongRepository {


    private val db = Firebase.firestore
    private val collection = "songs";
    fun save(song: Song) {
        db.collection(collection)
            .add(
                song.toMap()
            ).addOnSuccessListener {
                Log.d("ASAB Firebase", "Song created ${it.id}")
            }.addOnFailureListener {
                Log.d("ASAB Firebase", "Error $it")
            }
    }



    /*  fun getAll(): MutableList<Song> {
          val songs = mutableListOf<Song>()

          val query = FirebaseFirestore.getInstance().collection(collection).get()

          query.addOnSuccessListener {
              for (document in it.documents) {
                  val song = document.toObject(Song::class.java)
                  if (song != null) {
                      songs.add(song)
                  }
              }
          }.addOnFailureListener {
              Log.d("ASAB Firebase", "Error al obtener canciones: $it")
          }
          return songs
      }*/
    /*
        fun getAll(callback: (MutableList<Song>?, Exception?) -> Unit) {
            val collectionRef = db.collection("songs")
            val query = collectionRef.document("cTh18l92awOzmBn13mTw").get()

            query.addOnSuccessListener { documentSnapshot ->
                val songs = mutableListOf<Song>()
                val songData = documentSnapshot.data
                // Aquí deberías procesar los datos del documento y agregarlos a la lista songs
                // por ejemplo:
                val song = documentSnapshot.toObject(Song::class.java)
                song?.let { songs.add(it) }
                Log.d("PAU", "data $song")
                // Llama al callback con la lista de canciones
                callback(songs, null)
            }

            query.addOnFailureListener { exception ->
                Log.d("ASAB Firebase", "Error fetching songs: $exception")
                // Llama al callback con null para la lista de canciones y la excepción
                callback(null, exception)
            }
        }
    */
}