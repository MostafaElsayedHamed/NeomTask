package com.interview.mostafa.task.feature_home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.interview.mostafa.task.R
import com.interview.mostafa.task.core.component.ScreenTobBar
import com.interview.mostafa.task.core.spacing
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
                    LazyColumn {
                        items(viewModel.coffeeList, key = { it.id }) {
                            CoffeeItem(coffeeModel = it, onItemClicked = {

                            })
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
                text = stringResource(id =  R.string.empty_data),
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
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(MaterialTheme.spacing.twenty)
        .clickable {
            onItemClicked(coffeeModel)
        }) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier.height(MaterialTheme.spacing.hundred),
                model = ImageRequest.Builder(LocalContext.current).data(coffeeModel.image)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.coffee_image),
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.nine))
            Text(
                modifier = Modifier.padding(start = MaterialTheme.spacing.twentyTwo),
                text = coffeeModel.title,
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = coffeeModel.description,
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.eight))
        }
    }
}
