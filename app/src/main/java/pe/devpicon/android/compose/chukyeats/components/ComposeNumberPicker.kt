package pe.devpicon.android.compose.chukyeats.components

import androidx.compose.animation.animatedFloat
import androidx.compose.animation.core.ExponentialDecay
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.TargetAnimation
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.animation.FlingConfig
import androidx.compose.foundation.animation.fling
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.gesture.longPressGestureFilter
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.PathBuilder
import androidx.compose.ui.graphics.vector.VectorAssetBuilder
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import pe.devpicon.android.compose.chukyeats.ui.MyChukyEatsApplicationTheme
import kotlin.math.abs


@Composable
fun ComposableNumberPicker(
        state: MutableState<Int> = remember { mutableStateOf(0) },
        modifier: Modifier = Modifier
) {

    val numbersRowWidth = 36.dp
    val halvedNumberRowWidth = numbersRowWidth / 2
    val halvedNumberRowWidthPx = with(DensityAmbient.current) { halvedNumberRowWidth.toPx() }

    fun animatedStateValue(offset: Float): Int = state.value - (offset / halvedNumberRowWidthPx).toInt()

    val animatedOffset = animatedFloat(initVal = 0f)
    val coercedAnimatedOffset = animatedOffset.value % halvedNumberRowWidthPx
    val animatedStateValue = animatedStateValue(animatedOffset.value)

    Row(
            modifier = modifier
                    .draggable(
                            orientation = Orientation.Horizontal,
                            onDrag = { deltaX ->
                                animatedOffset.snapTo(
                                        targetValue = animatedOffset.value + deltaX
                                )
                            },
                            onDragStopped = { velocity ->
                                val config = FlingConfig(
                                        decayAnimation = ExponentialDecay(
                                                frictionMultiplier = 20f
                                        ),
                                        adjustTarget = { target ->
                                            val coercedTarget = target % halvedNumberRowWidthPx
                                            val coercedAnchors = listOf<Float>(
                                                    -halvedNumberRowWidthPx,
                                                    0f,
                                                    halvedNumberRowWidthPx
                                            )
                                            val coercedPoint = coercedAnchors.minByOrNull {
                                                abs(it - coercedTarget)
                                            }
                                            val base = halvedNumberRowWidthPx * (target / halvedNumberRowWidthPx).toInt()
                                            val adjusted = coercedPoint ?: 0f + base
                                            TargetAnimation(adjusted, SpringSpec())
                                        }
                                )
                                animatedOffset.fling(velocity, config = config) { _, endValue, _ ->
                                    state.value = animatedStateValue(endValue)
                                    animatedOffset.snapTo(0f)
                                }
                            }
                    )
    ) {
        val spacerWidth = 16.dp

        Arrow(direction = ArrowDirection.UP)
        Spacer(modifier = modifier.width(spacerWidth))
        Box(
                modifier = modifier
                        .align(Alignment.CenterVertically)
                        .offsetPx(
                                x = mutableStateOf(coercedAnimatedOffset)
                        )
        ) {
            Label(
                    text = (animatedStateValue - 1).toString(),
                    modifier = modifier
                            .offset(
                                    x = -halvedNumberRowWidth
                            )
                            .drawOpacity(coercedAnimatedOffset / halvedNumberRowWidthPx)
            )
            Label(
                    text = animatedStateValue.toString(),
                    modifier = modifier
                            .drawOpacity(
                                    1 - abs(coercedAnimatedOffset) / halvedNumberRowWidthPx
                            )
            )
            Label(
                    text = (animatedStateValue + 1).toString(),
                    modifier = modifier
                            .offset(
                                    x = halvedNumberRowWidth
                            )
                            .drawOpacity(-coercedAnimatedOffset / halvedNumberRowWidthPx)
            )
        }
        Spacer(modifier = modifier.width(spacerWidth))
        Arrow(direction = ArrowDirection.DOWN)
    }
}

@Composable
fun Label(
        text: String,
        modifier: Modifier = Modifier
) {
    Text(
            text = text,
            modifier = modifier.longPressGestureFilter {
                // Empty to disable text selection
            }
    )
}

enum class ArrowDirection {
    UP, DOWN
}

@Composable
fun Arrow(direction: ArrowDirection) {
    val vectorAsset = remember(direction) {
        VectorAssetBuilder(
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 2f,
                viewportHeight = 1f
        ).addPath(
                pathData = when (direction) {
                    ArrowDirection.UP -> PathBuilder()
                            .moveTo(0f, 1f)
                            .lineTo(1f, 0f)
                            .lineTo(2f, 1f)
                            .close()
                            .getNodes()
                    ArrowDirection.DOWN -> PathBuilder()
                            .moveTo(0f, 0f)
                            .lineTo(1f, 1f)
                            .lineTo(2f, 0f)
                            .close()
                            .getNodes()
                },
                fill = SolidColor(Color.Black),
                stroke = SolidColor(Color.White)
        ).build()
    }
    Image(asset = vectorAsset)
}


@Preview
@Composable
fun ArrowPreview() {
    MyChukyEatsApplicationTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            ComposableNumberPicker(
                    modifier = Modifier
                            .align(Alignment.Center)
            )
        }
    }
}