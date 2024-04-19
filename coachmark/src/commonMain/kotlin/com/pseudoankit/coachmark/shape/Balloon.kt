package com.pseudoankit.coachmark.shape

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * default tooltip shape provided from the library
 * @param arrow configurations of arrow to be shown in the view
 * @param modifier modifier to be applied in the composable
 * @param padding content padding of the view
 * @param cornerRadius corner radius of balloon shape
 * @param shadowElevation elevation when balloon is visible
 * @param bgColor background color of balloon
 * @param content content to be rendered inside balloon
 */
@Composable
public fun Balloon(
    arrow: Arrow = Arrow.Top(),
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {

    Box(
        modifier = Modifier
            .padding(
                start = arrow.startPadding,
                end = arrow.endPadding,
                top = arrow.topPadding,
                bottom = arrow.bottomPadding
            )
            .then(modifier),
        content = content
    )
    PaddingValues().calculateBottomPadding()
}

