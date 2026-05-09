/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Don't remove this copyright holder!
 */




package moe.mohsen.nmusic.viewmodels

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import moe.mohsen.nmusic.innertube.YouTube
import moe.mohsen.nmusic.innertube.pages.BrowseResult
import moe.mohsen.nmusic.constants.HideExplicitKey
import moe.mohsen.nmusic.constants.HideVideoKey
import moe.mohsen.nmusic.utils.dataStore
import moe.mohsen.nmusic.utils.get
import moe.mohsen.nmusic.utils.reportException
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YouTubeBrowseViewModel
@Inject
constructor(
    @ApplicationContext val context: Context,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val browseId = savedStateHandle.get<String>("browseId")!!
    private val params = savedStateHandle.get<String>("params")

    val result = MutableStateFlow<BrowseResult?>(null)

    init {
        viewModelScope.launch {
            YouTube
                .browse(browseId, params)
                .onSuccess {
                    val hideVideo = context.dataStore.get(HideVideoKey, false)
                    result.value = it.filterExplicit(context.dataStore.get(HideExplicitKey, false)).filterVideo(hideVideo)
                }.onFailure {
                    reportException(it)
                }
        }
    }
}
