package com.snakydh.asab_music_saver.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.snakydh.asab_music_saver.screens.DrumInstructionScreen
import com.snakydh.asab_music_saver.screens.DrumScreen
import com.snakydh.asab_music_saver.screens.HomeScreen
import com.snakydh.asab_music_saver.screens.MaracaInstructionScreen
import com.snakydh.asab_music_saver.screens.SaveSongLyricsScreen
import com.snakydh.asab_music_saver.screens.SongDetailScreen
import com.snakydh.asab_music_saver.viewModel.SongViewModel

@Composable
fun AppNavigation(context: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController, context, songViewModel = SongViewModel())
        }
        composable(route = AppScreens.SaveSongLyricsScreen.route) {
            SaveSongLyricsScreen(navController, viewModel = SongViewModel(), context)
        }
        composable(route = AppScreens.DrumScreen.route) {
            DrumScreen(navController = navController, context = context)
        }
        composable(route = AppScreens.DrumInstructionScreen.route) {
            DrumInstructionScreen(navController = navController)
        }
        composable(route = AppScreens.MaracaInstructionScreen.route) {
            MaracaInstructionScreen(navController = navController, context = context)
        }
        composable(route = AppScreens.SongDetailScreen.route) {
            SongDetailScreen(navController = navController, songViewModel = SongViewModel())
        }
    }
}