package com.pseudoankit.coachmark

import androidx.compose.ui.Modifier

public interface CoachMarkScope<T> {
    public fun Modifier.enableCoachMark(
        key: T,
        config: UnifyCoachMarkConfig
    ): Modifier

    public fun hide()

    public fun show(key: T)

    public fun show(keys: List<T>)

}