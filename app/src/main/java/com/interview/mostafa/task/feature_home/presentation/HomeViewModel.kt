package com.interview.mostafa.task.feature_home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.mostafa.task.core.Resource
import com.interview.mostafa.task.core.UiText
import com.interview.mostafa.task.feature_home.domain.model.CoffeeModel
import com.interview.mostafa.task.feature_home.domain.use_case.HomeUseCase
import com.interview.mostafa.task.feature_home.util.HomeEvents
import com.interview.mostafa.task.feature_home.util.HomeState
import com.interview.mostafa.task.feature_home.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    var coffeeList by mutableStateOf<List<CoffeeModel>>(emptyList())
        private set
    var homeState by mutableStateOf(HomeState())
        private set
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    var eventFlow = _eventFlow.asSharedFlow()

    init {
        getCoffeeList()
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            HomeEvents.Refresh -> {
                homeState = homeState.copy(isRefreshing = true)
                getCoffeeList()
            }
        }
    }

    private fun getCoffeeList() {
        viewModelScope.launch {
            homeState = homeState.copy(isLoading = true)
            homeUseCase().also { result ->
                homeState = homeState.copy(isLoading = false)
                homeState = homeState.copy(isRefreshing = false)
                when (result) {
                    is Resource.Success -> coffeeList = result.data ?: emptyList()
                    is Resource.Error -> {
                        _eventFlow.emit(
                            UiEvent.ShowToast(
                                result.uiText ?: UiText.unknownError()
                            )
                        )
                    }
                }
            }
        }
    }
}