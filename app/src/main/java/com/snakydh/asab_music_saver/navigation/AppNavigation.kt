package com.snakydh.asab_music_saver.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.snakydh.asab_music_saver.screens.DrumScreen
import com.snakydh.asab_music_saver.screens.HomeScreen
import com.snakydh.asab_music_saver.screens.SaveSongLyricsScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.SaveSongLyricsScreen.route) {
            SaveSongLyricsScreen(navController)
        }
        composable(route = AppScreens.DrumScreen.route){
            DrumScreen(navController = navController)
        }
    }

}