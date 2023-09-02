package com.pseudoankit.coachmark

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import com.pseudoankit.coachmark.model.OverlayClickEvent
import com.pseudoankit.coachmark.overlay.UnifyOverlayEffect
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.scope.CoachMarkScopeImpl
import com.pseudoankit.coachmark.ui.CoachMarkImpl
import com.pseudoankit.coachmark.util.CoachMarkDefaults
import com.pseudoankit.coachmark.util.CoachMarkKey

@Composable
public fun UnifyCoachmark(
    overlayEffect: UnifyOverlayEffect = CoachMarkDefaults.Overlay.background,
    onOverlayClicked: (CoachMarkKey) -> OverlayClickEvent = { CoachMarkDefaults.Overlay.clickEvent },
    overlayContent: @Composable CoachMarkScope.(CoachMarkKey) -> Unit,
    content: @Composable CoachMarkScope.() -> Unit
) {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current

    val scope = remember(onOverlayClicked) {
        CoachMarkScopeImpl(
            onOverlayClicked = onOverlayClicked,
            density = density,
            layoutDirection = layoutDirection
        )
    }

    CoachMarkImpl(
        overlayEffect = overlayEffect,
        content = content,
        scope = scope,
        overlayContent = overlayContent
    )
}
