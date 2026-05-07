/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Nothing OS Settings Quick Actions Component
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.koiverse.archivetune.ui.theme.NothingOSDesignSystem

/**
 * Settings Quick Action data class
 */
data class SettingsQuickActionUI(
    val icon: Painter,
    val label: String,
    val accentColor: Color = NothingOSDesignSystem.Colors.White,
    val onClick: () -> Unit,
)

/**
 * Settings Quick Actions Grid - 2×2 Bento layout with circular icon containers
 * Nothing OS design: Circular icons with 10% opacity background, subtle border
 */
@Composable
fun SettingsQuickActionsGrid(
    actions: List<SettingsQuickActionUI>,
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
        // 2-column grid layout
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Lg),
            verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Lg),
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(
                items = actions,
                key = { it.label }
            ) { action ->
                SettingsQuickActionGridItem(
                    action = action,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

/**
 * Individual settings quick action item with circular container
 */
@Composable
fun SettingsQuickActionGridItem(
    action: SettingsQuickActionUI,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { action.onClick() }
            .padding(NothingOSDesignSystem.Spacing.Md),
        verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Lg),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Circular icon container with accent color (10% opacity)
        Box(
            modifier = Modifier
                .size(72.dp)
                .background(
                    color = action.accentColor.copy(alpha = 0.1f),
                    shape = CircleShape,
                )
                .border(
                    width = 1.dp,
                    color = NothingOSDesignSystem.Colors.GlassBorder,
                    shape = CircleShape,
                )
                .clip(CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = action.icon,
                contentDescription = action.label,
                modifier = Modifier.size(32.dp),
                tint = action.accentColor,
            )
        }
        
        // Label below icon
        Text(
            text = action.label,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = NothingOSDesignSystem.Colors.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
