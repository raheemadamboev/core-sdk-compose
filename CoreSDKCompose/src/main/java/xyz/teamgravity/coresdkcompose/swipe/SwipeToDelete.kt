package xyz.teamgravity.coresdkcompose.swipe

import androidx.annotation.FloatRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

/**
 * Makes [content] swipeable for deleting. Only use this swipe for removing content from screen. This component is not intended for other purposes.
 *
 * @param model
 * Model.
 * @param onDelete
 * Called when content is swiped away. This is a place delete or remove item from main data.
 * @param duration
 * Duration of delete animation.
 * @param deleteIcon
 * Icon for delete.
 * @param enableDeleteFromStartToEnd
 * Is swiping from start to end enabled?
 * @param enableDeleteFromEndToStart
 * Is swiping from end to start enabled?
 * @param positionalThreshold
 * Positional threshold when swipe is considered active 0..1.
 * @param inactiveBackgroundColor
 * Background color when swipe position hasn't met positional threshold.
 * @param activeBackgroundColor
 * Background color when swipe position has met positional threshold.
 * @param inactiveIconTint
 * Tint color of icon when swipe position hasn't met positional threshold.
 * @param activeIconTint
 * Tint color of icon when swipe position has met positional threshold.
 * @param content
 * Content.
 */
@Composable
fun <T> SwipeToDelete(
    model: T,
    onDelete: (T) -> Unit,
    duration: Int,
    deleteIcon: ImageVector,
    enableDeleteFromStartToEnd: Boolean,
    enableDeleteFromEndToStart: Boolean,
    @FloatRange(from = 0.0, to = 1.0) positionalThreshold: Float = 0.7F,
    inactiveBackgroundColor: Color,
    activeBackgroundColor: Color,
    inactiveIconTint: Color,
    activeIconTint: Color,
    content: @Composable (T) -> Unit
) {
    var deleted by rememberSaveable { mutableStateOf(false) }
    val state = rememberSwipeToDismissBoxState(
        positionalThreshold = { distance ->
            distance * positionalThreshold
        }
    )

    LaunchedEffect(
        key1 = state.currentValue,
        block = {
            if (
                (enableDeleteFromStartToEnd && state.currentValue == SwipeToDismissBoxValue.StartToEnd) ||
                (enableDeleteFromEndToStart && state.currentValue == SwipeToDismissBoxValue.EndToStart)
            ) {
                deleted = true
            }
        }
    )

    LaunchedEffect(
        key1 = deleted,
        block = {
            if (deleted) {
                delay(duration.toLong())
                onDelete(model)
            }
        }
    )

    AnimatedVisibility(
        visible = !deleted,
        exit = shrinkVertically(
            animationSpec = tween(
                durationMillis = duration
            ),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismissBox(
            state = state,
            enableDismissFromStartToEnd = enableDeleteFromStartToEnd,
            enableDismissFromEndToStart = enableDeleteFromEndToStart,
            backgroundContent = {
                if (
                    (enableDeleteFromStartToEnd && state.dismissDirection == SwipeToDismissBoxValue.StartToEnd) ||
                    (enableDeleteFromEndToStart && state.dismissDirection == SwipeToDismissBoxValue.EndToStart)
                ) {
                    val alignment = when (state.dismissDirection) {
                        SwipeToDismissBoxValue.StartToEnd -> Alignment.CenterStart
                        SwipeToDismissBoxValue.EndToStart -> Alignment.CenterEnd
                        SwipeToDismissBoxValue.Settled -> return@SwipeToDismissBox
                    }
                    val backgroundColor by animateColorAsState(
                        targetValue = if (state.targetValue == SwipeToDismissBoxValue.Settled) inactiveBackgroundColor else activeBackgroundColor,
                        label = "backgroundColor"
                    )
                    val tint by animateColorAsState(
                        targetValue = if (state.targetValue == SwipeToDismissBoxValue.Settled) inactiveIconTint else activeIconTint,
                        label = "tint"
                    )
                    val scale by animateFloatAsState(
                        targetValue = if (state.targetValue == SwipeToDismissBoxValue.Settled) 0.75F else 1F,
                        label = "scale"
                    )

                    Box(
                        contentAlignment = alignment,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(backgroundColor)
                            .padding(horizontal = 20.dp)
                    ) {
                        Icon(
                            imageVector = deleteIcon,
                            contentDescription = null,
                            tint = tint,
                            modifier = Modifier.graphicsLayer {
                                scaleX = scale
                                scaleY = scale
                            }
                        )
                    }
                }
            },
            content = {
                content(model)
            }
        )
    }
}