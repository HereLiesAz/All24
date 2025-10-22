package com.hereliesaz.all24.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.util.lerp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalMultiBrowsePager(
    state: PagerState,
    modifier: Modifier = Modifier,
    preferredItemWidth: Dp,
    itemSpacing: Dp,
    contentPadding: PaddingValues,
    content: @Composable (page: Int) -> Unit,
) {
    HorizontalPager(
        state = state,
        modifier = modifier,
        pageSize = PageSize.Fixed(preferredItemWidth),
        contentPadding = contentPadding,
        pageSpacing = itemSpacing,
    ) { page ->
        val pageOffset = (
            (state.currentPage - page) + state.currentPageOffsetFraction
            ).absoluteValue
        val scale = lerp(1f, 0.85f, pageOffset)
        val alpha = lerp(1f, 0.5f, pageOffset)

        Box(
            modifier = Modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
        ) {
            content(page)
        }
    }
}
