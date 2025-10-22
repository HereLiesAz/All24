package com.hereliesaz.all24.ui.components.verticalcarousel.component

import androidx.compose.animation.core.spring
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable // <--- Ensure this import is here
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.scrollable // Single import for semantics scrollable
import androidx.compose.ui.platform.LocalDensity // <--- ADDED THIS IMPORT
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.hereliesaz.all24.ui.components.verticalcarousel.internal.KeylineState
import com.hereliesaz.all24.ui.components.verticalcarousel.internal.place
import com.hereliesaz.all24.ui.components.verticalcarousel.state.CarouselItemScope
import com.hereliesaz.all24.ui.components.verticalcarousel.state.CarouselItemScopeImpl
import com.hereliesaz.all24.ui.components.verticalcarousel.state.CarouselState

@Composable
fun VerticalMultiBrowseCarousel(
    state: CarouselState,
    modifier: Modifier = Modifier,
    preferredItemHeight: Dp,
    itemSpacing: Dp = 0.dp,
    flingBehavior: FlingBehavior = state.fling(0f, spring()),
    content: @Composable CarouselItemScope.(itemIndex: Int) -> Unit
) {
    val density = LocalDensity.current // <--- GET CURRENT DENSITY
    state.keylineState = remember(preferredItemHeight, itemSpacing, state.itemCount(), density) { // <--- ADDED DENSITY TO REMEMBER KEYS
        KeylineState(
            density = density, // <--- PASSED DENSITY
            itemHeight = preferredItemHeight,
            itemSpacing = itemSpacing,
            itemCount = state.itemCount(),
            strategy = KeylineState.Strategy.MultiBrowse
        )
    }

    Layout(
        modifier = modifier
            .scrollable( // <--- Should resolve now
                orientation = Orientation.Vertical,
                state = state.scrollableState,
                flingBehavior = flingBehavior
            )
            .semantics {
                scrollable(
                    state = state.scrollableState,
                    orientation = Orientation.Vertical,
                    reverseScrolling = true
                )
            },
        content = {
            for (i in 0 until state.itemCount()) {
                Box {
                    val scope = CarouselItemScopeImpl(
                        carouselItemInfo = state.keylineState.getItemInfo(i)
                    )
                    scope.content(i)
                }
            }
        }
    ) { measurables, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            measurables.forEachIndexed { index, measurable ->
                place(
                    index = index,
                    measurable = measurable,
                    constraints = constraints,
                    keylineState = state.keylineState
                )
            }
        }
    }
}
