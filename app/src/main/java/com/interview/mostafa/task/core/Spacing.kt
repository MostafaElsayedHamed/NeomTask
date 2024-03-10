package com.interview.mostafa.task.core

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val noSpace: Dp = Dp.Hairline,
    val zero: Dp = 0.dp,
    val one: Dp = 1.dp,
    val two: Dp = 2.dp,
    val three: Dp = 3.dp,
    val four: Dp = 4.dp,
    val five: Dp = 5.dp,
    val six: Dp = 6.dp,
    val seven: Dp = 7.dp,
    val eight: Dp = 8.dp,
    val nine: Dp = 9.dp,
    val ten: Dp = 10.dp,
    val eleven: Dp = 11.dp,
    val twelve: Dp = 12.dp,
    val thirteen: Dp = 13.dp,
    val fourteen: Dp = 14.dp,
    val fifteen: Dp = 15.dp,
    val sixteen: Dp = 16.dp,
    val seventeen: Dp = 17.dp,
    val eighteen: Dp = 18.dp,
    val twenty: Dp = 20.dp,
    val twentyTwo: Dp = 22.dp,
    val twentyFour: Dp = 24.dp,
    val twentyFive: Dp = 25.dp,
    val twentySix: Dp = 26.dp,
    val twentySeven: Dp = 27.dp,
    val twentyEight: Dp = 28.dp,
    val thirty: Dp = 30.dp,
    val thirtyTwo: Dp = 32.dp,
    val thirtySix: Dp = 36.dp,
    val thirtyEight: Dp = 38.dp,
    val forty: Dp = 40.dp,
    val fortyTwo: Dp = 42.dp,
    val fortyFour: Dp = 44.dp,
    val fortyFive: Dp = 45.dp,
    val fortySix: Dp = 46.dp,
    val fortyEight: Dp = 48.dp,
    val fortyNine: Dp = 49.dp,
    val fifty: Dp = 50.dp,
    val fiftyFour: Dp = 54.dp,
    val fiftySix: Dp = 56.dp,
    val fiftyEight: Dp = 58.dp,
    val sixty: Dp = 60.dp,
    val sixtyTwo: Dp = 62.dp,
    val sixtyFour: Dp = 64.dp,
    val sixtyFive: Dp = 65.dp,
    val sixtySix: Dp = 66.dp,
    val sixtyEight: Dp = 68.dp,
    val eighty: Dp = 80.dp,
    val ninetyFour: Dp = 94.dp,
    val ninetySix: Dp = 96.dp,
    val ninetyEight: Dp = 98.dp,
    val hundred: Dp = 100.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }
val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
