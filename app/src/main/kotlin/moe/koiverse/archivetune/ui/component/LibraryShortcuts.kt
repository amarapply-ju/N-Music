/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Nothing OS Library Shortcuts Component
 */

package moe.koiverse.archivetune.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.koiverse.archivetune.ui.theme.NothingOSDesignSystem

/**
 * Library Shortcut - Circular icon badge for Liked, Downloaded, etc.
 * Following Nothing OS design: circular container with 10% opacity icon color background
 */
data class LibraryShortcut(
    val id: String,
    val label: String,
    val icon: Int,
    val accentColor: Color = NothingOSDesignSystem.Colors.White,
    val onClick: () -> Unit,
)

@Composable
fun LibraryShortcutsBento(
    shortcuts: List<LibraryShortcut>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = NothingOSDesignSystem.Colors.Surface,
                shape = RoundedCornerShape(NothingOSDesignSystem.BorderRadius.Card)
            )
            .border(
                width = 1.dp,
                color = NothingOSDesignSystem.Colors.GlassBorder,
                shape = RoundedCornerShape(NothingOSDesignSystem.BorderRadius.Card)
            )
            .padding(NothingOSDesignSystem.Spacing.Lg),
        verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Lg),
    ) {
        // Grid of circular shortcuts
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Lg),
            verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Lg),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(
                items = shortcuts,
                key = { it.id }
            ) { shortcut ->
                LibraryShortcutItem(
                    shortcut = shortcut,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
fun LibraryShortcutItem(
    shortcut: LibraryShortcut,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Sm),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Circular icon container
        Box(
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = shortcut.accentColor.copy(alpha = 0.1f),
                    shape = CircleShape,
                )
                .border(
                    width = 1.dp,
                    color = NothingOSDesignSystem.Colors.GlassBorder,
                    shape = CircleShape,
                )
                .clickable { shortcut.onClick() }
                .clip(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(shortcut.icon),
                contentDescription = shortcut.label,
                modifier = Modifier.size(28.dp),
                tint = shortcut.accentColor,
            )
        }
        
        // Label
        Text(
            text = shortcut.label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Normal,
            color = NothingOSDesignSystem.Colors.White,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
        )
    }
}

/**
 * Simplified single row library shortcuts (for top-level shortcuts)
 */
@Composable
fun LibraryShortcutsRow(
    shortcuts: List<LibraryShortcut>,
    modifier: Modifier = Modifier,
) {
    androidx.compose.foundation.layout.Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(NothingOSDesignSystem.Spacing.Lg),
        horizontalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Lg),
    ) {
        shortcuts.forEach { shortcut ->
            LibraryShortcutItem(
                shortcut = shortcut,
                modifier = Modifier.weight(1f),
            )
        }
    }
}
