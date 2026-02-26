package xyz.teamgravity.coresdkcompose.configuration.screen

import androidx.window.core.layout.WindowSizeClass

enum class ScreenConfiguration {
    PhonePortrait,
    PhoneLandscape,
    TabletPortrait,
    TabletLandscape,
    Desktop;

    companion object {
        fun fromWindowSizeClass(value: WindowSizeClass): ScreenConfiguration = when {
            value.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)
                    && value.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND) -> Desktop

            value.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)
                    && value.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND) -> TabletLandscape

            value.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
                    && value.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_EXPANDED_LOWER_BOUND) -> TabletPortrait

            value.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)
                    && !value.isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND) -> PhoneLandscape

            else -> PhonePortrait
        }
    }
}