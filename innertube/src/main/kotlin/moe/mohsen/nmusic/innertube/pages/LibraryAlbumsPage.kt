/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Don't remove this copyright holder!
 */




package moe.mohsen.nmusic.innertube.pages

import moe.mohsen.nmusic.innertube.models.Album
import moe.mohsen.nmusic.innertube.models.AlbumItem
import moe.mohsen.nmusic.innertube.models.Artist
import moe.mohsen.nmusic.innertube.models.ArtistItem
import moe.mohsen.nmusic.innertube.models.MusicResponsiveListItemRenderer
import moe.mohsen.nmusic.innertube.models.MusicTwoRowItemRenderer
import moe.mohsen.nmusic.innertube.models.PlaylistItem
import moe.mohsen.nmusic.innertube.models.SongItem
import moe.mohsen.nmusic.innertube.models.YTItem
import moe.mohsen.nmusic.innertube.models.oddElements
import moe.mohsen.nmusic.innertube.utils.parseTime

data class LibraryAlbumsPage(
    val albums: List<AlbumItem>,
    val continuation: String?,
) {
    companion object {
        fun fromMusicTwoRowItemRenderer(renderer: MusicTwoRowItemRenderer): AlbumItem? {
            val browseId = renderer.navigationEndpoint.browseEndpoint?.browseId ?: return null
            val playlistId = renderer.thumbnailOverlay?.musicItemThumbnailOverlayRenderer?.content
                ?.musicPlayButtonRenderer?.playNavigationEndpoint
                ?.watchPlaylistEndpoint?.playlistId
                ?: renderer.menu?.menuRenderer?.items?.firstOrNull()
                    ?.menuNavigationItemRenderer?.navigationEndpoint
                    ?.watchPlaylistEndpoint?.playlistId
                ?: browseId.removePrefix("MPREb_").let { "OLAK5uy_$it" }

            return AlbumItem(
                browseId = browseId,
                playlistId = playlistId,
                title = renderer.title.runs?.firstOrNull()?.text ?: return null,
                artists = null,
                year = renderer.subtitle?.runs?.lastOrNull()?.text?.toIntOrNull(),
                thumbnail = renderer.thumbnailRenderer.musicThumbnailRenderer?.getThumbnailUrl() ?: return null,
                explicit = renderer.subtitleBadges?.find {
                    it.musicInlineBadgeRenderer?.icon?.iconType == "MUSIC_EXPLICIT_BADGE"
                } != null
            )
        }
    }
}
