package com.pseudoankit.coachmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pseudoankit.coachmark.model.CoachMarkConfig
import com.pseudoankit.coachmark.model.CoachMarkGlobalConfig
import com.pseudoankit.coachmark.overlay.OverlayClickEvent
import com.pseudoankit.coachmark.scope.CoachMarkScope
import com.pseudoankit.coachmark.shape.Arrow
import com.pseudoankit.coachmark.shape.ArrowToolTipShape
import com.pseudoankit.coachmark.ui.CoachMarkImpl

@Composable
public fun UnifyCoachmark(
    config: CoachMarkGlobalConfig,
    content: @Composable CoachMarkScope.() -> Unit
) {
    CoachMarkImpl(
        content = content,
        config = config
    )
}

private enum class Keys { First, Second }

@Preview
@Composable
public fun UnifyCoachmarkDemo() {
    Box(modifier = Modifier.fillMaxSize()) {

        UnifyCoachmark(
            config = CoachMarkGlobalConfig(
                tooltip = CoachMarkGlobalConfig.Tooltip(
                    padding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                    shape = ArrowToolTipShape(Arrow.End(18.dp, 10.dp))
                ),
                overlay = CoachMarkGlobalConfig.Overlay(
                    onClick = {
                        OverlayClickEvent.GoNext
                    }
                )
            )
        ) {
            Column {
                Button(
                    onClick = {
                        show(Keys.First, Keys.Second)
                    },
                    modifier = Modifier
                        .enableCoachMark(
                            CoachMarkConfig(
                                tooltip = CoachMarkConfig.Tooltip(
                                    text = "demo1"
                                ),
                                key = Keys.First
                            )
                        )
                ) {
                    Text(text = "Click me 1")
                }

                Button(
                    onClick = {
                        show(Keys.Second, Keys.First)
                    },
                    modifier = Modifier.enableCoachMark(
                        CoachMarkConfig(
                            tooltip = CoachMarkConfig.Tooltip(
                                text = "demo2"
                            ),
                            key = Keys.Second
                        )
                    )
                ) {
                    Text(text = "Click me 2")
                }
            }
        }
    }
}