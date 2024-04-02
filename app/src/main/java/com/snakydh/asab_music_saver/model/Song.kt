package com.snakydh.asab_music_saver.model

data class Song(
    val id: String = "",
    val title: String = "",
    val lyrics: String = ""
) {
    fun toMap(): MutableMap<String, String> {
        return mutableMapOf(
            "title" to title,
            "lyrics" to lyrics,
        )
    }
}