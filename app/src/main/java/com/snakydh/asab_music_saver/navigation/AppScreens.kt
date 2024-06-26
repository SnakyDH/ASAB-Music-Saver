package com.snakydh.asab_music_saver.navigation

sealed class AppScreens(val route: String) {
    data object HomeScreen : AppScreens("home_screen")
    data object SaveSongLyricsScreen : AppScreens("save_song_lyrics_screen")
    data object DrumScreen : AppScreens("drum_screen")
    data object DrumInstructionScreen : AppScreens("drum_instruction_screen")
    data object MaracaInstructionScreen : AppScreens("maraca_instruction_screen")
    data object MaracaScreen : AppScreens("maraca_screen")
    data object SongDetailScreen : AppScreens("song_detail_screen")

}