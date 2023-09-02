package com.pseudoankit.coachmark.overlay

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import com.pseudoankit.coachmark.model.TooltipConfig
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.util.rememberTooltipHolder

public class DimOverlayEffect(
    private val color: Color = Color.Black.copy(alpha = .75f)
) : UnifyOverlayEffect {

    @Composable
    override fun CoachMarkScope.Overlay(
        modifier: Modifier,
        content: @Composable () -> Unit
    ) {

        val density = LocalDensity.current
        val currentVisibleTooltip by rememberTooltipHolder(
            item = currentVisibleTooltip,
            animationSpec = tooltipAnimationSpec()
        )

        Box(
            modifier = modifier
                .graphicsLayer(alpha = .99f)
                .drawBehind {
                    drawRect(color)
                    currentVisibleTooltip.item?.let { tooltip ->
                        highlightActualView(tooltip, density, currentVisibleTooltip.alpha)
                    }
                }
        ) {
            content()
        }
    }

    private fun DrawScope.highlightActualView(
        toolTip: TooltipConfig,
        density: Density,
        alpha: Float
    ) {
        val path = toolTip.highlightedViewShape.pathToHighlight(
            density = density,
            size = Size(
                width = toolTip.layout.width.toFloat(),
                height = toolTip.layout.height.toFloat(),
            )
        ).apply {
            translate(
                Offset(
                    x = toolTip.layout.startX,
                    y = toolTip.layout.startY,
                )
            )
        }

        drawPath(
            path = path,
            color = Color.Black,
            alpha = alpha,
            blendMode = BlendMode.DstOut,
        )
    }
}