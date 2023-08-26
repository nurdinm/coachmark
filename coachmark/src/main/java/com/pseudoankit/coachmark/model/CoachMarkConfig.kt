package com.pseudoankit.coachmark.model

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.pseudoankit.coachmark.CoachMarkDefaults
import com.pseudoankit.coachmark.CoachMarkKey
import com.pseudoankit.coachmark.internal.util.createToolTipModifier

public data class CoachMarkConfig(
    val tooltip: Tooltip,
    val overlay: Overlay = Overlay(),
    val key: CoachMarkKey
) {

    public data class Overlay(
        val color: Color? = null,
        val onClick: ((key: CoachMarkKey) -> OverlayClickEvent)? = null,
    )

    public data class Tooltip(
        val text: String,
        val textColor: Color? = null,
        val modifier: Modifier? = null,
        val placement: ToolTipPlacement
    ) {

        public constructor(
            text: String,
            textColor: Color,
            bgColor: Color,
            shape: Shape,
            padding: PaddingValues = CoachMarkDefaults.Tooltip.padding,
            placement: ToolTipPlacement
        ) : this(
            textColor = textColor,
            modifier = createToolTipModifier(bgColor, shape, padding),
            text = text,
            placement = placement
        )
    }
}
