/*
 * ArchiveTune Project Original (2026)
 * Chartreux Westia (github.com/koiverse)
 * Licensed Under GPL-3.0 | see git history for contributors
 * Don't remove this copyright holder!
 */

@file:OptIn(ExperimentalMaterial3ExpressiveApi::class)

package moe.mohsen.nmusic.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Badge
import androidx.compose.material3.FloatingToolbarDefaults
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import moe.mohsen.nmusic.R
import moe.mohsen.nmusic.ui.screens.Screens

@Composable
fun FloatingNavigationToolbar(
    items: List<Screens>,
    pureBlack: Boolean,
    modifier: Modifier = Modifier,
    onFabClick: (() -> Unit)? = null,
    fabIconRes: Int? = null,
    fabContentDescription: String = "",
    onShuffleClick: (() -> Unit)? = null,
    shuffleIconRes: Int? = null,
    shuffleContentDescription: String = "",
    onHistoryClick: (() -> Unit)? = null,
    onStatsClick: (() -> Unit)? = null,
    onSettingsClick: (() -> Unit)? = null,
    isSelected: (Screens) -> Boolean,
    onItemClick: (Screens, Boolean) -> Unit,
) {
    val toolbarContainerColor = floatingToolbarContainerColor(pureBlack = pureBlack)
    val toolbarColors = FloatingToolbarDefaults.standardFloatingToolbarColors(
        toolbarContainerColor = toolbarContainerColor,
    )
    val hasOverflowAction =
        (onShuffleClick != null && shuffleIconRes != null) ||
            onHistoryClick != null ||
            onStatsClick != null ||
            onSettingsClick != null
    val hasFabAction = onFabClick != null && fabIconRes != null

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        val showSelectedLabels = maxWidth >= 360.dp

        if (hasOverflowAction) {
            HorizontalFloatingToolbar(
                expanded = true,
                floatingActionButton = {
                    FloatingToolbarOverflowAction(
                        pureBlack = pureBlack,
                        onShuffleClick = onShuffleClick,
                        shuffleIconRes = shuffleIconRes,
                        shuffleContentDescription = shuffleContentDescription,
                        onHistoryClick = onHistoryClick,
                        onStatsClick = onStatsClick,
                        onSettingsClick = onSettingsClick,
                    )
                },
                modifier = Modifier.widthIn(max = 480.dp),
                colors = toolbarColors,
            ) {
                items.forEach { screen ->
                    val selected = isSelected(screen)

                    FloatingNavigationToolbarItem(
                        screen = screen,
                        selected = selected,
                        showSelectedLabel = showSelectedLabels,
                        pureBlack = pureBlack,
                        onClick = { onItemClick(screen, selected) },
                    )
                }
            }
        } else if (hasFabAction) {
            HorizontalFloatingToolbar(
                expanded = true,
                floatingActionButton = {
                    FloatingToolbarFabAction(
                        pureBlack = pureBlack,
                        onClick = onFabClick,
                        iconRes = fabIconRes,
                        contentDescription = fabContentDescription,
                    )
                },
                modifier = Modifier.widthIn(max = 480.dp),
                colors = toolbarColors,
            ) {
                items.forEach { screen ->
                    val selected = isSelected(screen)

                    FloatingNavigationToolbarItem(
                        screen = screen,
                        selected = selected,
                        showSelectedLabel = showSelectedLabels,
                        pureBlack = pureBlack,
                        onClick = { onItemClick(screen, selected) },
                    )
                }
            }
        } else {
            HorizontalFloatingToolbar(
                expanded = true,
                modifier = Modifier.widthIn(max = 420.dp),
                colors = toolbarColors,
            ) {
                items.forEach { screen ->
                    val selected = isSelected(screen)

                    FloatingNavigationToolbarItem(
                        screen = screen,
                        selected = selected,
                        showSelectedLabel = showSelectedLabels,
                        pureBlack = pureBlack,
                        onClick = { onItemClick(screen, selected) },
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatingToolbarOverflowAction(
    pureBlack: Boolean,
    onShuffleClick: (() -> Unit)?,
    shuffleIconRes: Int?,
    shuffleContentDescription: String,
    onHistoryClick: (() -> Unit)?,
    onStatsClick: (() -> Unit)?,
    onSettingsClick: (() -> Unit)?,
) {
    var fabMenuExpanded by rememberSaveable { mutableStateOf(false) }

    Box {
        FloatingToolbarDefaults.VibrantFloatingActionButton(
            onClick = { fabMenuExpanded = !fabMenuExpanded },
            containerColor = floatingToolbarFabContainerColor(pureBlack = pureBlack),
            contentColor = floatingToolbarFabContentColor(pureBlack = pureBlack),
        ) {
            Icon(
                painter = painterResource(R.drawable.more_horiz),
                contentDescription =
                    shuffleContentDescription.ifEmpty {
                        stringResource(R.string.more)
                    },
            )
        }

        DropdownMenu(
            expanded = fabMenuExpanded,
            onDismissRequest = { fabMenuExpanded = false },
            shape = RoundedCornerShape(24.dp),
            containerColor = if (pureBlack) Color.Black else MaterialTheme.colorScheme.surfaceContainerHigh,
            tonalElevation = 6.dp,
        ) {
            if (onHistoryClick != null) {
                OverflowMenuItem(
                    text = stringResource(R.string.history),
                    iconRes = R.drawable.history,
                    pureBlack = pureBlack,
                    onClick = {
                        fabMenuExpanded = false
                        onHistoryClick()
                    },
                )
            }

            if (onStatsClick != null) {
                OverflowMenuItem(
                    text = stringResource(R.string.stats),
                    iconRes = R.drawable.stats,
                    pureBlack = pureBlack,
                    onClick = {
                        fabMenuExpanded = false
                        onStatsClick()
                    },
                )
            }

            if (onSettingsClick != null) {
                OverflowMenuItem(
                    text = stringResource(R.string.settings),
                    iconRes = R.drawable.settings,
                    pureBlack = pureBlack,
                    onClick = {
                        fabMenuExpanded = false
                        onSettingsClick()
                    },
                    badge = true,
                )
            }

            if (onShuffleClick != null && shuffleIconRes != null) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.shuffle)) },
                    onClick = {
                        fabMenuExpanded = false
                        onShuffleClick()
                    },
                    leadingIcon = {
                        Surface(
                            modifier = Modifier.size(40.dp),
                            shape = CircleShape,
                            color = floatingToolbarMenuIconContainerColor(pureBlack = pureBlack),
                            contentColor = floatingToolbarMenuIconContentColor(pureBlack = pureBlack),
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    painter = painterResource(shuffleIconRes),
                                    contentDescription =
                                        shuffleContentDescription.ifEmpty {
                                            stringResource(R.string.shuffle)
                                        },
                                )
                            }
                        }
                    },
                    colors =
                        MenuDefaults.itemColors(
                            textColor = if (pureBlack) Color.White else MaterialTheme.colorScheme.onSurface,
                            leadingIconColor = if (pureBlack) Color.White.copy(alpha = 0.82f) else MaterialTheme.colorScheme.onSurfaceVariant,
                        ),
                )
            }
        }
    }
}

@Composable
private fun OverflowMenuItem(
    text: String,
    iconRes: Int,
    pureBlack: Boolean,
    onClick: () -> Unit,
    badge: Boolean = false,
) {
    DropdownMenuItem(
        text = { Text(text) },
        onClick = onClick,
        leadingIcon = {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                color = floatingToolbarMenuIconContainerColor(pureBlack = pureBlack),
                contentColor = floatingToolbarMenuIconContentColor(pureBlack = pureBlack),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (badge) {
                        BadgedBox(badge = { Badge() }) {
                            Icon(
                                painter = painterResource(iconRes),
                                contentDescription = text,
                            )
                        }
                    } else {
                        Icon(
                            painter = painterResource(iconRes),
                            contentDescription = text,
                        )
                    }
                }
            }
        },
        colors =
            MenuDefaults.itemColors(
                textColor = if (pureBlack) Color.White else MaterialTheme.colorScheme.onSurface,
                leadingIconColor = if (pureBlack) Color.White.copy(alpha = 0.82f) else MaterialTheme.colorScheme.onSurfaceVariant,
            ),
    )
}

@Composable
private fun FloatingToolbarFabAction(
    pureBlack: Boolean,
    onClick: (() -> Unit)?,
    iconRes: Int?,
    contentDescription: String,
) {
    if (onClick == null || iconRes == null) return

    FloatingToolbarDefaults.VibrantFloatingActionButton(
        onClick = onClick,
        containerColor = floatingToolbarFabContainerColor(pureBlack = pureBlack),
        contentColor = floatingToolbarFabContentColor(pureBlack = pureBlack),
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription =
                contentDescription.ifEmpty {
                    stringResource(R.string.create_playlist)
                },
        )
    }
}

@Composable
private fun FloatingNavigationToolbarItem(
    screen: Screens,
    selected: Boolean,
    showSelectedLabel: Boolean,
    pureBlack: Boolean,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(24.dp)
    val containerColor by animateColorAsState(
        targetValue =
            when {
                selected -> floatingToolbarSelectedItemContainerColor(pureBlack = pureBlack)
                else -> Color.Transparent
            },
        label = "",
    )
    val contentColor by animateColorAsState(
        targetValue =
            when {
                selected -> floatingToolbarSelectedItemContentColor(pureBlack = pureBlack)
                else -> floatingToolbarItemContentColor(pureBlack = pureBlack)
            },
        label = "",
    )
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.91f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "",
    )
    val showLabel = selected && showSelectedLabel && screen.route != Screens.Search.route

    Row(
        modifier =
            Modifier
                .scale(scale)
                .animateContentSize()
                .clip(shape)
                .background(color = containerColor, shape = shape)
                .clickable(
                    interactionSource = interactionSource,
                    indication = LocalIndication.current,
                    role = Role.Tab,
                    onClick = onClick,
                )
                .widthIn(min = 48.dp)
                .padding(
                    horizontal = 12.dp,
                    vertical = 10.dp,
                ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(if (selected) screen.iconIdActive else screen.iconIdInactive),
                contentDescription = stringResource(screen.titleId),
                tint = contentColor,
            )

            Spacer(modifier = Modifier.size(6.dp))

            if (selected) {
                // Nothing OS red dot indicator for active state
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            color = Color(0xFFFF2E00), // Nothing Red
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(contentColor)
                )
            }
        }
    }
}

@Composable
private fun floatingToolbarContainerColor(pureBlack: Boolean): Color {
    // Nothing OS glassmorphic pill: semi-transparent surface with theme awareness
    return if (pureBlack) {
        Color(0x80141414) // Pure black for extra dark mode
    } else {
        MaterialTheme.colorScheme.surfaceContainer.copy(alpha = 0.9f)
    }
}

@Composable
private fun floatingToolbarFabContainerColor(pureBlack: Boolean): Color {
    return if (pureBlack) Color.White.copy(alpha = 0.12f) else MaterialTheme.colorScheme.tertiaryContainer
}

@Composable
private fun floatingToolbarFabContentColor(pureBlack: Boolean): Color {
    return if (pureBlack) Color.White else MaterialTheme.colorScheme.onTertiaryContainer
}

@Composable
private fun floatingToolbarSelectedItemContainerColor(pureBlack: Boolean): Color {
    return if (pureBlack) Color.White.copy(alpha = 0.12f) else MaterialTheme.colorScheme.secondaryContainer
}

@Composable
private fun floatingToolbarSelectedItemContentColor(pureBlack: Boolean): Color {
    return if (pureBlack) Color.White else MaterialTheme.colorScheme.onSecondaryContainer
}

@Composable
private fun floatingToolbarItemContentColor(pureBlack: Boolean): Color {
    return if (pureBlack) {
        Color.White.copy(alpha = 0.82f)
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
}

@Composable
private fun floatingToolbarMenuIconContainerColor(pureBlack: Boolean): Color {
    return if (pureBlack) Color.White.copy(alpha = 0.12f) else MaterialTheme.colorScheme.secondaryContainer
}

@Composable
private fun floatingToolbarMenuIconContentColor(pureBlack: Boolean): Color {
    return if (pureBlack) Color.White else MaterialTheme.colorScheme.onSecondaryContainer
}
