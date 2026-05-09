/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Don't remove this copyright holder!
 */

package moe.mohsen.nmusic.musicrecognition

import androidx.navigation.NavController

const val MusicRecognitionRoute = "music_recognition"
const val ACTION_MUSIC_RECOGNITION = "moe.mohsen.nmusic.action.MUSIC_RECOGNITION"
const val MusicRecognitionAutoStartRequestKey = "music_recognition_auto_start_request"

fun NavController.openMusicRecognition(
    autoStartRequestId: Long = System.currentTimeMillis(),
) {
    val currentRoute = currentDestination?.route
    if (currentRoute != MusicRecognitionRoute && !popBackStack(MusicRecognitionRoute, inclusive = false)) {
        navigate(MusicRecognitionRoute) {
            launchSingleTop = true
        }
    }

    getBackStackEntry(MusicRecognitionRoute).savedStateHandle[MusicRecognitionAutoStartRequestKey] =
        autoStartRequestId
}
