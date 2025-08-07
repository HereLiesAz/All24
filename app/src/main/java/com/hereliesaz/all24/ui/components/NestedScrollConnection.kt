package com.hereliesaz.all24.ui.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import kotlin.math.abs

object ParentVerticalScrollConsumer : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        // If the scroll is vertical, consume it entirely.
        // If the scroll is horizontal, consume nothing.
        return if (abs(available.y) > abs(available.x)) {
            available
        } else {
            Offset.Zero
        }
    }
}
