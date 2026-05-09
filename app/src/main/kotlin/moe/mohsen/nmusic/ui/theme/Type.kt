/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Don't remove this copyright holder!
 */

package moe.mohsen.nmusic.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import moe.mohsen.nmusic.R

// Nothing OS Font Families
private val NdotFont = FontFamily(Font(R.font.ndot55))
private val NTypeFont = FontFamily(Font(R.font.ntype82))
private val NTypeMonoFont = FontFamily(Font(R.font.ntype82mono))
private val PoppinsFont = FontFamily(Font(R.font.poppins))

/**
 * N-music Typography - Nothing OS Design Language
 * Uses Ndot-55 for display (dot-matrix aesthetic) and NType-82 for headlines/titles
 */
fun buildNothingTypography(useSystemFont: Boolean = false): Typography {
    val displayFont = if (useSystemFont) PoppinsFont else NdotFont
    val headlineFont = if (useSystemFont) PoppinsFont else NTypeFont
    val bodyFont = PoppinsFont

    return Typography(
        // Display - Large, distinctive text using Ndot dot-matrix font
        displayLarge = TextStyle(
            fontFamily = displayFont,
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        ),
        displayMedium = TextStyle(
            fontFamily = displayFont,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = TextStyle(
            fontFamily = displayFont,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),
        // Headlines - Using NType-82 for Nothing OS aesthetic
        headlineLarge = TextStyle(
            fontFamily = headlineFont,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = TextStyle(
            fontFamily = headlineFont,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = TextStyle(
            fontFamily = headlineFont,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        // Titles - NType-82 for consistent Nothing branding
        titleLarge = TextStyle(
            fontFamily = headlineFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = TextStyle(
            fontFamily = headlineFont,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.02).sp
        ),
        titleSmall = TextStyle(
            fontFamily = headlineFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.02).sp
        ),
        // Body text - Poppins for readability with tight letter-spacing (Nothing OS style)
        bodyLarge = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = (-0.02).sp
        ),
        bodyMedium = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.02).sp
        ),
        bodySmall = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = (-0.02).sp
        ),
        // Labels
        labelLarge = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = TextStyle(
            fontFamily = bodyFont,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )
}

// Default typography with Nothing OS fonts
val AppTypography = buildNothingTypography(useSystemFont = false)
val SystemTypography = buildNothingTypography(useSystemFont = true)

