/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Nothing OS Typography Extensions
 */

package moe.koiverse.archivetune.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Nothing OS Typography System
 * - NDot57 for titles/branding (dot-matrix aesthetic)
 * - Inter/Sans-Serif for body (clean, modern)
 * - Tight letter-spacing (-0.02em) for premium feel
 */
fun createNothingOSTypography(): Typography {
    return Typography(
        // Display Large
        displayLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Display Medium
        displayMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 26.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Display Small
        displaySmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Headline Large
        headlineLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Headline Medium
        headlineMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Headline Small
        headlineSmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Title Large
        titleLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Title Medium
        titleMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Title Small
        titleSmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp,
            lineHeight = 18.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Body Large
        bodyLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 22.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Body Medium
        bodyMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Body Small
        bodySmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 18.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Label Large
        labelLarge = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Label Medium
        labelMedium = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 18.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
        // Label Small
        labelSmall = TextStyle(
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            lineHeight = 16.sp,
            letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
        ),
    )
}
