package com.interview.mostafa.task.feature_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.interview.mostafa.task.R
import com.interview.mostafa.task.core.component.ScreenTobBar
import com.interview.mostafa.task.core.spacing

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier.background(color = Color.LightGray),
        topBar = {
            ScreenTobBar(
                title = viewModel.title,
                showBackIcon = true,
                onBackPressed = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                modifier = Modifier.size(MaterialTheme.spacing.twoHundred),
                model = ImageRequest.Builder(LocalContext.current).data(viewModel.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.coffee_image),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.eight))
            Text(
                modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.twenty),
                text = viewModel.description,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.eight))
            LazyColumn {
                items(viewModel.ingredients) {
                    Text(
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.twenty),
                        text = it.toString(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}