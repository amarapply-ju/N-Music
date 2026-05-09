/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Nothing OS Component Library
 */

package moe.mohsen.nmusic.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.mohsen.nmusic.ui.theme.NothingOSDesignSystem

/**
 * Bento Box Card - Nothing OS Bento layout component
 * Features: 28dp border radius, glass border, clean spacing
 */
@Composable
fun BentoCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
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
            .padding(NothingOSDesignSystem.Spacing.Xl),
    ) {
        content()
    }
}

/**
 * Bento Row - Horizontal container for grid layout
 */
@Composable
fun BentoRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(NothingOSDesignSystem.Spacing.Lg),
    ) {
        content()
    }
}

/**
 * Dot Matrix Badge - Small accent element  
 */
@Composable
fun DotMatrixBadge(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .then(
                Modifier
                    .background(
                        color = NothingOSDesignSystem.Colors.AccentRed,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .padding(4.dp)
            )
    ) {}
}

/**
 * Glass Border Container - For subtle separation
 */
@Composable
fun GlassBorderContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = NothingOSDesignSystem.Colors.GlassBorder,
                shape = RoundedCornerShape(NothingOSDesignSystem.BorderRadius.Card)
            )
            .padding(NothingOSDesignSystem.Spacing.Md),
    ) {
        content()
    }
}
