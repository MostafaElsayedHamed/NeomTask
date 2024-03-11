package com.interview.mostafa.task.core.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.interview.mostafa.task.R
import com.interview.mostafa.task.core.spacing

@Composable
fun ScreenTobBar(
    title: String,
    showBackIcon: Boolean = true,
    showTitle: Boolean = true,
    onBackPressed: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.spacing.fiftySix)
            .background(color = Color.White)

    ) {
        if (showBackIcon) {
            IconButton(
                onClick = onBackPressed,
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.sixteen,
                    bottom = MaterialTheme.spacing.sixteen
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_back),
                    contentDescription = stringResource(id = R.string.back_arrow),

                    )
            }
        }

        if (showTitle) {
            Text(
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Default,
                text = title,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .padding(horizontal = if (showBackIcon) MaterialTheme.spacing.fortyFour else MaterialTheme.spacing.sixteen),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}