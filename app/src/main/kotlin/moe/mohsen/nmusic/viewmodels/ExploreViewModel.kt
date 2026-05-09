/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Don't remove this copyright holder!
 */




package moe.mohsen.nmusic.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import moe.mohsen.nmusic.innertube.YouTube
import moe.mohsen.nmusic.innertube.models.filterExplicit
import moe.mohsen.nmusic.innertube.models.filterVideo
import moe.mohsen.nmusic.innertube.pages.ExplorePage
import moe.mohsen.nmusic.constants.HideExplicitKey
import moe.mohsen.nmusic.constants.HideVideoKey
import moe.mohsen.nmusic.db.MusicDatabase
import moe.mohsen.nmusic.utils.dataStore
import moe.mohsen.nmusic.utils.get
import moe.mohsen.nmusic.utils.reportException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel
@Inject
constructor(
    @ApplicationContext val context: Context,
    val database: MusicDatabase,
) : ViewModel() {
    val explorePage = MutableStateFlow<ExplorePage?>(null)

    private suspend fun load() {
        YouTube
            .explore()
            .onSuccess { page ->
                val artists: MutableMap<Int, String> = mutableMapOf()
                val favouriteArtists: MutableMap<Int, String> = mutableMapOf()
                database.allArtistsByPlayTime().first().let { list ->
                    var favIndex = 0
                    for ((artistsIndex, artist) in list.withIndex()) {
                        artists[artistsIndex] = artist.id
                        if (artist.artist.bookmarkedAt != null) {
                            favouriteArtists[favIndex] = artist.id
                            favIndex++
                        }
                    }
                }
                explorePage.value =
                    page.copy(
                        newReleaseAlbums =
                        page.newReleaseAlbums
                            .sortedBy { album ->
                                val artistIds = album.artists.orEmpty().mapNotNull { it.id }
                                val firstArtistKey =
                                    artistIds.firstNotNullOfOrNull { artistId ->
                                        if (artistId in favouriteArtists.values) {
                                            favouriteArtists.entries.firstOrNull { it.value == artistId }?.key
                                        } else {
                                            artists.entries.firstOrNull { it.value == artistId }?.key
                                        }
                                    } ?: Int.MAX_VALUE
                                firstArtistKey
                            }.filterExplicit(context.dataStore.get(HideExplicitKey, false)).filterVideo(context.dataStore.get(HideVideoKey, false)),
                    )
            }.onFailure {
                reportException(it)
            }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            load()
        }
    }
}
