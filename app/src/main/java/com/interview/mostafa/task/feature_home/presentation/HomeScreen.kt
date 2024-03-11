package com.interview.mostafa.task.feature_home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.interview.mostafa.task.R
import com.interview.mostafa.task.core.ScreenRoutes
import com.interview.mostafa.task.core.component.ScreenTobBar
import com.interview.mostafa.task.core.spacing
import com.interview.mostafa.task.core.utli.Constants
import com.interview.mostafa.task.core.utli.toast
import com.interview.mostafa.task.feature_home.domain.model.CoffeeModel
import com.interview.mostafa.task.feature_home.util.HomeEvents
import com.interview.mostafa.task.feature_home.util.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.homeState
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> navController.navigate(event.route)
                is UiEvent.ShowToast -> context.toast(event.uiText)
                else -> {}
            }
        }
    }
    Scaffold(
        modifier = Modifier.background(color = Color.LightGray),
        topBar = {
            ScreenTobBar(
                title = stringResource(id = R.string.home_title),
                showBackIcon = false,
                onBackPressed = {
                    navController.popBackStack()
                })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = state.isRefreshing),
                onRefresh = {
                    viewModel.onEvent(HomeEvents.Refresh)
                }
            ) {
                if (viewModel.coffeeList.isNotEmpty()) {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .fillMaxWidth(),
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(
                            MaterialTheme.spacing.sixteen
                        ),
                        verticalArrangement = Arrangement.spacedBy(
                            MaterialTheme.spacing.sixteen
                        ),
                    ) {
                        items(viewModel.coffeeList, key = { it.id }) {
                            CoffeeItem(coffeeModel = it,
                                onItemClicked = { coffeeModel ->
                                    navController.navigate(
                                        ScreenRoutes.Details.route.plus(
                                            "?${Constants.TITLE}=${coffeeModel.title}" +
                                                    "&${Constants.DESCRIPTION}=${coffeeModel.description}" +
                                                    "&${Constants.IMAGE_URL}=${coffeeModel.image}" +
                                                    "&${Constants.INGREDIENTS}=${coffeeModel.ingredients}"
                                        )
                                    )
                                }
                            )
                        }
                    }
                } else {
                    EmptyState()
                }
            }
        }
    }
}

@Composable
fun EmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.fifty))

            Text(
                text = stringResource(id = R.string.empty_data),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun CoffeeItem(
    coffeeModel: CoffeeModel,
    onItemClicked: (CoffeeModel) -> Unit
) {
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    Box(modifier = Modifier
        .width((screenWidthDp / 2).dp - MaterialTheme.spacing.twenty)
        .clip(RoundedCornerShape(MaterialTheme.spacing.twelve))
        .background(color = Color.White)
        .padding(MaterialTheme.spacing.twenty)
        .clickable {
            onItemClicked(coffeeModel)
        }) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.height(MaterialTheme.spacing.hundred),
                model = ImageRequest.Builder(LocalContext.current).data(coffeeModel.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.coffee_image),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.eight))
            Text(
                text = coffeeModel.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.eight))
        }
    }
}
