/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Nothing OS Header Chips Component
 */

package moe.mohsen.nmusic.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import moe.mohsen.nmusic.ui.theme.NothingOSDesignSystem

/**
 * Header Chip data class
 */
data class NothingOSHeaderChip(
    val id: String,
    val label: String,
    val isActive: Boolean = false,
    val onClick: () -> Unit,
)

/**
 * Nothing OS Header Chips Row
 * Switches between outline (inactive) and solid (active) styles
 * Active: Solid white background with black text
 * Inactive: Outline only with white borders
 */
@Composable
fun NothingOSHeaderChipsRow(
    chips: List<NothingOSHeaderChip>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.padding(horizontal = NothingOSDesignSystem.Spacing.Lg),
        horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Sm),
    ) {
        chips.forEach { chip ->
            NothingOSHeaderChipItem(
                chip = chip,
                modifier = Modifier,
            )
        }
    }
}

/**
 * Individual header chip item
 */
@Composable
fun NothingOSHeaderChipItem(
    chip: NothingOSHeaderChip,
    modifier: Modifier = Modifier,
) {
    // Animate colors between active and inactive states
    val backgroundColor by animateColorAsState(
        targetValue = if (chip.isActive) NothingOSDesignSystem.Colors.White else Color.Transparent,
        label = "chipBg"
    )
    
    val textColor by animateColorAsState(
        targetValue = if (chip.isActive) NothingOSDesignSystem.Colors.Background else NothingOSDesignSystem.Colors.White,
        label = "chipText"
    )
    
    val borderColor by animateColorAsState(
        targetValue = if (chip.isActive) Color.Transparent else NothingOSDesignSystem.Colors.White,
        label = "chipBorder"
    )
    
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(100.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(100.dp)
            )
            .clickable { chip.onClick() }
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = chip.label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
        )
    }
}

/**
 * Full-width chip in stadium shape (for larger chips)
 */
@Composable
fun NothingOSStadiumChip(
    label: String,
    isActive: Boolean = false,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isActive) NothingOSDesignSystem.Colors.White else Color.Transparent,
        label = "stadiumBg"
    )
    
    val textColor by animateColorAsState(
        targetValue = if (isActive) NothingOSDesignSystem.Colors.Background else NothingOSDesignSystem.Colors.White,
        label = "stadiumText"
    )
    
    val borderColor by animateColorAsState(
        targetValue = if (isActive) Color.Transparent else NothingOSDesignSystem.Colors.White,
        label = "stadiumBorder"
    )
    
    Box(
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(NothingOSDesignSystem.BorderRadius.Pill)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(NothingOSDesignSystem.BorderRadius.Pill)
            )
            .clickable { onClick() }
            .padding(horizontal = NothingOSDesignSystem.Spacing.Lg, vertical = NothingOSDesignSystem.Spacing.Md),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = textColor,
        )
    }
}
