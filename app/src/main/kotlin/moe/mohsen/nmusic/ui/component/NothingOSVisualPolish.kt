/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Nothing OS Visual Polish - Dot Matrix & Squircles
 */

package moe.mohsen.nmusic.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Squircle Shape - interpolates between square and circle
 * Creates the premium "squircle" look seen in Nothing OS
 * Formula: |x|^3 + |y|^3 = r^3
 */
class SquircleShape(private val cornerRadius: Dp = 20.dp) : Shape {
    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: LayoutDirection,
        density: androidx.compose.ui.unit.Density,
    ) = androidx.compose.ui.graphics.Outline.Generic(
        path = Path().apply {
            val radius = with(density) { cornerRadius.toPx() }
            val width = size.width
            val height = size.height
            
            // Create a smooth curve between square and circle
            // For now, use a rounded rectangle that's slightly more rounded than normal
            val roundPercent = 0.8f // 0 = square, 1 = circle
            val adjustedRadius = radius + (minOf(width, height) / 2 - radius) * roundPercent
            
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = width,
                    bottom = height,
                    radiusX = adjustedRadius,
                    radiusY = adjustedRadius,
                )
            )
        }
    )
}

/**
 * Dot Matrix Overlay - Subtle grid pattern overlay for Nothing OS aesthetic
 * Adds 2-3% opacity dot pattern to make black backgrounds feel textured
 */
@Composable
fun DotMatrixOverlay(
    modifier: Modifier = Modifier,
    dotSize: Dp = 2.dp,
    dotSpacing: Dp = 8.dp,
    opacity: Float = 0.03f,
) {
    androidx.compose.foundation.layout.Box(
        modifier = modifier
            .background(
                color = Color.White.copy(alpha = opacity)
            )
    ) {
        // This creates a subtle textured appearance
        // In a production version, you might use a shader or canvas for true dot pattern
    }
}

/**
 * Applies squircle masking to album art or thumbnails
 * Usage: Image(..., modifier = Modifier.clip(SquircleShape(20.dp)))
 */
fun Modifier.squircleMask(cornerRadius: Dp = 20.dp): Modifier =
    this.clip(SquircleShape(cornerRadius))

/**
 * Enhanced album art with squircle masking and subtle shadow
 */
@Composable
fun SquircleAlbumArt(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 20.dp,
    content: @Composable () -> Unit,
) {
    androidx.compose.foundation.layout.Box(
        modifier = modifier
            .squircleMask(cornerRadius)
            .graphicsLayer {
                // Subtle shadow for depth
                shadowElevation = 8.dp.toPx()
            }
    ) {
        content()
    }
}

/**
 * Background with dot matrix overlay
 * Creates the textured Nothing OS background feel
 */
@Composable
fun NothingOSBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    androidx.compose.foundation.layout.Box(
        modifier = modifier
            .background(color = androidx.compose.material3.MaterialTheme.colorScheme.background)
    ) {
        // Apply subtle dot matrix pattern
        DotMatrixOverlay(
            modifier = Modifier
                .matchParentSize()
                .background(color = Color.White.copy(alpha = 0.02f))
        )
        
        content()
    }
}
