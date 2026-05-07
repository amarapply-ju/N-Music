/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Nothing OS Design System Constants
 */

package moe.koiverse.archivetune.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Nothing OS 4.1 Design System
 * Aggressive monochrome minimalism with dot-matrix aesthetic
 */
object NothingOSDesignSystem {
    // === Colors ===
    object Colors {
        val Background = Color(0xFF000000)        // Pure Black
        val Surface = Color(0xFF121212)           // Card Dark
        val AccentRed = Color(0xFFFF2E00)         // Nothing Red (active states only)
        val White = Color(0xFFFFFFFF)
        val Grey100 = Color(0xFFF0F0F0)
        val Grey50 = Color(0xFFFAFAFA)
        val Grey900 = Color(0xFF1A1A1A)
        val Grey800 = Color(0xFF2A2A2A)
        val GreyDim = Color(0xFF606060)
        
        // Scrim/overlay for glass effect
        val GlassDark = Color(0x80141414)         // rgba(20, 20, 20, 0.5)
        val GlassBorder = Color(0x0DFFFFFF)       // rgba(255, 255, 255, 0.05)
    }
    
    // === Spacing & Layout ===
    object Spacing {
        val Xs = 4.dp
        val Sm = 8.dp
        val Md = 12.dp
        val Lg = 16.dp
        val Xl = 20.dp
        val Xxl = 24.dp
    }
    
    // === Border Radius ===
    object BorderRadius {
        val Pill = 100.dp              // For buttons, pills
        val Card = 28.dp               // For cards, sheets
        val Small = 8.dp               // For small elements
    }
    
    // === Typography ===
    object Typography {
        // Letter spacing for tight, modern look
        val TightSpacing = -0.02.sp    // body text
        val DisplaySize = 20.sp        // Logo
        val HeadlineSize = 18.sp       // Section headers
        val BodySize = 14.sp           // Body text
        val CaptionSize = 12.sp        // Captions
    }
    
    // === Component Sizes ===
    object ComponentSize {
        val NavBarHeight = 56.dp
        val TopAppBarHeight = 56.dp
        val IconSize = 24.dp
        val ButtonHeight = 48.dp
    }
}
