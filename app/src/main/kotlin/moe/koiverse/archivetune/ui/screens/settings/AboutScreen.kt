package moe.koiverse.archivetune.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import moe.koiverse.archivetune.BuildConfig
import moe.koiverse.archivetune.LocalPlayerAwareWindowInsets
import moe.koiverse.archivetune.R
import moe.koiverse.archivetune.ui.component.IconButton
import moe.koiverse.archivetune.ui.theme.NothingOSDesignSystem
import moe.koiverse.archivetune.ui.utils.backToMain

@Composable
@Suppress("UNUSED_PARAMETER")
fun AboutScreen(navController: NavController, scrollBehavior: TopAppBarScrollBehavior) {
    val uriHandler = LocalUriHandler.current

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.about)) },
                navigationIcon = {
                    IconButton(
                        onClick = navController::navigateUp,
                        onLongClick = navController::backToMain,
                    ) {
                        Icon(
                            painterResource(R.drawable.arrow_back),
                            contentDescription = null,
                        )
                    }
                },
                windowInsets = LocalPlayerAwareWindowInsets.current.only(
                    WindowInsetsSides.Top + WindowInsetsSides.Horizontal,
                ),
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NothingOSDesignSystem.Colors.Background)
                .padding(paddingValues)
                .windowInsetsPadding(
                    LocalPlayerAwareWindowInsets.current.only(
                        WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom,
                    ),
                )
                .verticalScroll(rememberScrollState())
                .padding(horizontal = NothingOSDesignSystem.Spacing.Lg),
            verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Xl),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // === Header: N-music Logo ===
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = NothingOSDesignSystem.Spacing.Lg),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Sm),
            ) {
                Text(
                    "N-music",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = NothingOSDesignSystem.Colors.White,
                )
                Text(
                    "v${BuildConfig.VERSION_NAME}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = NothingOSDesignSystem.Colors.GreyDim,
                )
            }

            // === About Card (Bento Box) ===
            Column(
                modifier = Modifier
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
                verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Md),
            ) {
                Text(
                    stringResource(R.string.about),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NothingOSDesignSystem.Colors.White,
                )
                Text(
                    "Simple music player for Nothing Phone.",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = NothingOSDesignSystem.Colors.Grey100,
                    letterSpacing = NothingOSDesignSystem.Typography.TightSpacing,
                )
            }

            // === Developer Section ===
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = NothingOSDesignSystem.Spacing.Md),
                verticalArrangement = Arrangement.spacedBy(NothingOSDesignSystem.Spacing.Lg),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "Connect with Developer",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = NothingOSDesignSystem.Colors.GreyDim,
                )

                // === Instagram Button (Pill Style) ===
                Button(
                    onClick = { uriHandler.openUri("https://instagram.com/ali.mohsen.maruf") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(NothingOSDesignSystem.ComponentSize.ButtonHeight),
                    shape = RoundedCornerShape(NothingOSDesignSystem.BorderRadius.Pill),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NothingOSDesignSystem.Colors.Surface,
                        contentColor = NothingOSDesignSystem.Colors.White,
                    ),
                ) {
                    Text(
                        "Follow on Instagram",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }

            // === Spacer for bottom breathing room ===
            Column(
                modifier = Modifier.height(NothingOSDesignSystem.Spacing.Xl)
            ) {}
        }
    }
}